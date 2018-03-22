package cloudoer.su.service.impl;

import cloudoer.su.entity.Teacher;
import cloudoer.su.exception.ServiceException;
import cloudoer.su.service.TeacherService;
import cloudoer.su.base.impl.BaseServiceImpl;
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

@Service("teacherService")
@Transactional
public class TeacherServiceImpl extends BaseServiceImpl implements TeacherService {
    public List<Teacher> getAll (){
        return teacherDao.getAll();
    }

    public List<Teacher> getByPage(int pageNo, int pageSize){
        return teacherDao.getByPage(pageNo, pageSize);
    }

    public Teacher getById(String id){
        return (Teacher) teacherDao.getById(id);
    }

    public Teacher getByNumber(String number){
        return (Teacher) teacherDao.getByNumber(number);
    }

    public String add(Teacher teacher){
        return teacherDao.add(teacher);
    }

    public void update(Teacher teacher){
        Teacher t = (Teacher) teacherDao.getById(teacher.getId());
        if (t == null){
            throw new ServiceException("修改失败，请检查参数");
        }
        t.setName(teacher.getName());
        t.setNumber(teacher.getNumber());
        t.setSex(teacher.getSex());
        t.setIdCard(teacher.getIdCard());
        t.setPhone(teacher.getPhone());
        t.setQq(teacher.getQq());
        t.setEmail(teacher.getEmail());
        t.setState(teacher.getState());
    }

    public void delete (String id){
        Teacher t = (Teacher) teacherDao.getById(id);
        if (t == null){
            throw new ServiceException("删除失败，请检查参数");
        }
        teacherDao.delete(id);
    }

    public String importFile(File file) throws ServiceException {
        HSSFWorkbook workbook = null;
        StringBuffer msg = new StringBuffer();
        try {
            workbook = new HSSFWorkbook(new FileInputStream(file));
            HSSFSheet sheet = workbook.getSheetAt(0);
            int cont = sheet.getLastRowNum()+1;
            Teacher t = null;
            int successCount = 0;
            int errorCount = 0;
            for (int i = 1; i < cont; i++){
                try {
                    t = new Teacher();
                    t.setName(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(0)));
                    t.setNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(1)));
                    t.setSex(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(2)));
                    t.setIdCard(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(3)));
                    t.setPhone(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(4)));
                    t.setQq(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(5)));
                    t.setEmail(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(6)));
                    t.setState(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(7)));
                    teacherDao.add(t);
                    successCount ++ ;
                    if (i+1%20==0){
                        teacherDao.getSession().flush();
                        teacherDao.getSession().clear();
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
        List<Teacher> teachers = teacherDao.getAll();
        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet1");
        //创建一行
        HSSFRow rows = sheet.createRow(0);
        //创建这一行的一个单元格
        rows.createCell(0).setCellValue("姓名");
        rows.createCell(1).setCellValue("教师编号");
        rows.createCell(2).setCellValue("性别");
        rows.createCell(3).setCellValue("电话");
        rows.createCell(5).setCellValue("qq号码");
        rows.createCell(6).setCellValue("邮箱");
        rows.createCell(7).setCellValue("状态");
        for (int i = 0; i < teachers.size(); i++){
            rows = sheet.createRow(i+1);
            rows.createCell(0).setCellValue(teachers.get(i).getName());
            rows.createCell(1).setCellValue(teachers.get(i).getNumber());
            rows.createCell(2).setCellValue(teachers.get(i).getSex());
            rows.createCell(3).setCellValue(teachers.get(i).getPhone());
            rows.createCell(5).setCellValue(teachers.get(i).getQq());
            rows.createCell(6).setCellValue(teachers.get(i).getEmail());
            rows.createCell(7).setCellValue(teachers.get(i).getState());
        }
        workbook.write(os);
    }


}
