package cloudoer.su.service.impl;

import cloudoer.su.base.impl.BaseServiceImpl;
import cloudoer.su.entity.ClassCommittee;
import cloudoer.su.entity.Classes;
import cloudoer.su.entity.Position;
import cloudoer.su.entity.Student;
import cloudoer.su.exception.ServiceException;
import cloudoer.su.service.ClassCommitteeService;
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

@Service("classCommitteeService")
@Transactional
public class ClassCommitteeServiceImpl extends BaseServiceImpl implements ClassCommitteeService {
    public List<ClassCommittee> getAll() {
        return classCommitteeDao.getAll();
    }

    public List<ClassCommittee> getByPage(int pageNo, int pageSize) {
        return classCommitteeDao.getByPage(pageNo,pageSize);
    }

    public ClassCommittee getById(String id) {
        return (ClassCommittee) classCommitteeDao.getById(id);
    }

    public ClassCommittee getByNumber(String number) {
        return (ClassCommittee) classCommitteeDao.getByNumber(number);
    }

    public String add(String classesNumber, String studentNumber, String positionNumber) {
        Classes c = (Classes) classesDao.getByNumber(classesNumber);
        Student s = (Student) studentDao.getByNumber(studentNumber);
        Position p = (Position) positionDao.getByNumber(positionNumber);
        if (c == null || s == null || p == null){
            throw new ServiceException("添加失败，参数错误");
        }
        ClassCommittee classCommittee = new ClassCommittee();
        classCommittee.setClassesC(c);
        classCommittee.setStudent(s);
        classCommittee.setPosition(p);
        classCommittee.setStudentName(s.getName());
        classCommittee.setPositionName(p.getName());
        classCommittee.setPositionNumber(p.getNumber());
        return classCommitteeDao.add(classCommittee);
    }

    public void update(String classesNumber, String studentNumber, String positionNumber, String classCommitteeId) {
        Classes c = (Classes) classesDao.getByNumber(classesNumber);
        Student s = (Student) studentDao.getByNumber(studentNumber);
        Position p = (Position) positionDao.getByNumber(positionNumber);
        ClassCommittee committee = (ClassCommittee) classCommitteeDao.getById(classCommitteeId);
        if (c == null || s == null || p == null || committee == null){
            throw new ServiceException("修改失败，参数错误");
        }
        committee.setClassesC(c);
        committee.setStudent(s);
        committee.setPosition(p);
        committee.setStudentName(s.getName());
        committee.setPositionName(p.getName());
        committee.setPositionNumber(p.getNumber());
    }

    public void delete(String id) {
        ClassCommittee committee = (ClassCommittee) classCommitteeDao.getById(id);
        if (committee == null){
            throw new ServiceException("删除失败，参数错误");
        }
        classCommitteeDao.delete(id);
    }

    public String importFile(File file) throws ServiceException {
        HSSFWorkbook workbook = null;
        StringBuffer msg = new StringBuffer();
        try{
            workbook = new HSSFWorkbook(new FileInputStream(file));
            HSSFSheet sheet = workbook.getSheetAt(0);
            int cont = sheet.getLastRowNum()+1;
            Classes c = null;
            Student s = null;
            Position p = null;
            ClassCommittee committee = null;
            int successCount = 0;
            int errorCount = 0;
            for (int i = 1; i < cont; i++){
                try{
                    committee = new ClassCommittee();
                    c = (Classes) classesDao.getByNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(0)));
                    s = (Student) studentDao.getByNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(1)));
                    p = (Position) positionDao.getByNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(2)));
                    if (c == null || s == null || p == null){
                        errorCount++;
                        msg.append("[第"+(i+1)+"行]原因:参数错误" );
                        msg.append("<br/>");
                    }
                    committee.setClassesC(c);
                    committee.setStudent(s);
                    committee.setPosition(p);
                    committee.setStudentName(s.getName());
                    committee.setPositionName(p.getName());
                    committee.setPositionNumber(p.getNumber());
                    classCommitteeDao.add(committee);
                    successCount++;
                    if (i+1%20==0){
                        classCommitteeDao.getSession().flush();
                        classCommitteeDao.getSession().clear();
                    }
                }catch (ServiceException e){
                    errorCount++;
                    msg.append("[第"+(i+1)+"行]原因:" + e.getErrorMsg());
                    msg.append("<br/>");
                }catch (Exception e){
                    errorCount++;
                    msg.append("[第"+(i+1)+"行]原因:不明...<br/>");
                }
                msg.insert(0,"文件上传成功<br/>导入成功:"+ successCount +"条" + "<br/>导入失败:"+errorCount+ "条 <br/>记录<br/>");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("导入失败，解析文件错误，请检查文件格式");
        }
        return msg.toString();
    }

    public void exportFile(OutputStream os) throws Exception {
        List<ClassCommittee> list = classCommitteeDao.getAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow rows = sheet.createRow(0);
        rows.createCell(0).setCellValue("班级名称");
        rows.createCell(1).setCellValue("班级编号");
        rows.createCell(2).setCellValue("职务");
        rows.createCell(3).setCellValue("学生姓名");
        rows.createCell(4).setCellValue("学号");
        rows.createCell(5).setCellValue("联系电话");
        rows.createCell(6).setCellValue("qq");
        for (int i = 0; i < list.size(); i++){
            rows = sheet.createRow(i+1);
            if (list.get(i).getClassesC() != null){
                rows.createCell(0).setCellValue(list.get(i).getClassesC().getName());
                rows.createCell(1).setCellValue(list.get(i).getClassesC().getNumber());
            }
            rows.createCell(2).setCellValue(list.get(i).getPositionName());
            rows.createCell(3).setCellValue(list.get(i).getStudentName());
            if (list.get(i).getStudent() != null){
                rows.createCell(4).setCellValue(list.get(i).getStudent().getNumber());
                rows.createCell(5).setCellValue(list.get(i).getStudent().getPhone());
                rows.createCell(6).setCellValue(list.get(i).getStudent().getQq());
            }
        }
        workbook.write(os);
    }
}
