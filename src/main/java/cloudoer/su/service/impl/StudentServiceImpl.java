package cloudoer.su.service.impl;

import cloudoer.su.base.impl.BaseServiceImpl;
import cloudoer.su.entity.Classes;
import cloudoer.su.entity.Dormitory;
import cloudoer.su.entity.Student;
import cloudoer.su.exception.ServiceException;
import cloudoer.su.service.StudentService;
import cloudoer.su.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service("studentService")
@Transactional
public class StudentServiceImpl extends BaseServiceImpl implements StudentService {
    public List<Student> getAll() {
        return studentDao.getAll();
    }

    public List<Student> getByPage(int pageNo, int pageSize) {
        return studentDao.getByPage(pageNo,pageSize);
    }

    public Student getById(String id) {
        return (Student) studentDao.getById(id);
    }

    public Student getByNumber(String number) {
        return (Student) studentDao.getByNumber(number);
    }

    public String add(Student student, String classesId, String dormitoryId) throws Exception{
        student.setClasses((Classes) classesDao.getById(classesId));
        try {
            student.setDormitory((Dormitory) dormitoryDao.getById(dormitoryId));
        }catch (Exception e){
            e.printStackTrace();
        }
        return studentDao.add(student);
    }

    public void update(Student student, String classesId, String dormitoryId) throws Exception {
        Student s = (Student) studentDao.getById(student.getId());
        if (s == null){
            throw new ServiceException("修改失败，请检查参数");
        }
        s.setClasses((Classes) classesDao.getById(classesId));
        try {
            s.setDormitory((Dormitory) dormitoryDao.getById(dormitoryId));
        }catch (Exception e){
            e.printStackTrace();
        }
        s.setName(student.getName());
        s.setNumber(student.getNumber());
        s.setSex(student.getSex());
        s.setIdCard(student.getIdCard());
        s.setPhone(student.getPhone());
        s.setQq(student.getQq());
        s.setEmail(student.getEmail());
        s.setState(student.getState());
    }

    public void delete(String id) {
        Student s = (Student) studentDao.getById(id);
        if (s == null){
            throw new ServiceException("删除失败，请检查参数");
        }
        studentDao.delete(id);
    }

    public String importFile(File file) throws ServiceException {
        HSSFWorkbook workbook = null;
        StringBuffer msg = new StringBuffer();
        try {
            workbook = new HSSFWorkbook(new FileInputStream(file));
            HSSFSheet sheet = workbook.getSheetAt(0);
            int cont = sheet.getLastRowNum()+1;
            Student s = null;
            int successCount = 0;
            int errorCount = 0;
            for (int i = 1; i < cont; i++){
                try {
                    s = new Student();
                    s.setName(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(0)));
                    s.setNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(1)));
                    s.setSex(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(2)));
                    s.setIdCard(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(3)));
                    s.setPhone(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(4)));
                    s.setQq(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(5)));
                    s.setEmail(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(6)));
                    s.setState(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(7)));
                    s.setClasses((Classes) classesDao.getByNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(8))));
                    s.setDormitory((Dormitory) dormitoryDao.getByNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(9))));
                    studentDao.add(s);
                    successCount ++ ;
                    if (i+1%20==0){
                        studentDao.getSession().flush();
                        studentDao.getSession().clear();
                    }
                }catch (ServiceException e){
                    errorCount++;
                    msg.append("[第"+(i+1)+"行]原因:" + e.getErrorMsg());
                    msg.append("<br/>");
                }catch (Exception e){
                    errorCount++;
                    msg.append("[第"+(i+1)+"行]原因:不明...<br/>");
                }
            }
            msg.insert(0,"文件上传成功<br/>导入成功:"+ successCount +"条" + "<br/>导入失败:"+errorCount+ "条 <br/>记录<br/>");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("导入失败，解析文件错误，请检查文件格式");
        }
        return msg.toString();
    }

    public void exportFile(OutputStream os) throws Exception {
        List<Student> students = studentDao.getAll();
        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet1");
        //创建一行
        HSSFRow rows = sheet.createRow(0);
        //创建这一行的一个单元格
        rows.createCell(0).setCellValue("姓名");
        rows.createCell(1).setCellValue("学号");
        rows.createCell(2).setCellValue("性别");
        rows.createCell(3).setCellValue("身份证号码");
        rows.createCell(4).setCellValue("电话");
        rows.createCell(5).setCellValue("qq号码");
        rows.createCell(6).setCellValue("邮箱");
        rows.createCell(7).setCellValue("状态");
        rows.createCell(8).setCellValue("班级");
        rows.createCell(9).setCellValue("寝室");
        for (int i = 0; i < students.size(); i++){
            rows = sheet.createRow(i+1);
            rows.createCell(0).setCellValue(students.get(i).getName());
            rows.createCell(1).setCellValue(students.get(i).getNumber());
            rows.createCell(2).setCellValue(students.get(i).getSex());
            rows.createCell(3).setCellValue(students.get(i).getIdCard());
            rows.createCell(4).setCellValue(students.get(i).getPhone());
            rows.createCell(5).setCellValue(students.get(i).getQq());
            rows.createCell(6).setCellValue(students.get(i).getEmail());
            rows.createCell(7).setCellValue(students.get(i).getState());
            if (students.get(i).getClasses() != null){
                rows.createCell(8).setCellValue(students.get(i).getClasses().getName());
            }
            if (students.get(i).getDormitory() != null){
                rows.createCell(9).setCellValue(students.get(i).getDormitory().getName());
            }
        }
        workbook.write(os);
    }
}
