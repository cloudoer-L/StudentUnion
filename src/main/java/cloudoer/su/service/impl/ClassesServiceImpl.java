package cloudoer.su.service.impl;

import cloudoer.su.base.impl.BaseServiceImpl;
import cloudoer.su.entity.Classes;
import cloudoer.su.entity.Teacher;
import cloudoer.su.exception.ServiceException;
import cloudoer.su.service.ClassesService;
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

@Service("classesService")
@Transactional
public class ClassesServiceImpl extends BaseServiceImpl implements ClassesService {
    public List<Classes> getAll() {
        return classesDao.getAll();
    }

    public List<Classes> getByPage(int pageNo, int pageSize) {
        return classesDao.getByPage(pageNo, pageSize);
    }

    public Classes getById(String id) {
        return (Classes) classesDao.getById(id);
    }

    public Classes getByNumber(String number) {
        return (Classes) classesDao.getByNumber(number);
    }

    public String add(Classes classes, String teacherId) {
        Teacher teacher = (Teacher) teacherDao.getById(teacherId);
        classes.setTeacher(teacher);
        return classesDao.add(classes);
    }

    public void update(Classes classes, String teacherId) {
        Classes c = (Classes) classesDao.getById(classes.getId());
        if (c == null){
            throw new ServiceException("修改失败，请检查参数");
        }
        c.setName(classes.getName());
        c.setNumber(classes.getNumber());
        c.setGrade(classes.getGrade());
        c.setState(classes.getState());
    }

    public void delete(String id) {
        Classes c = (Classes) classesDao.getById(id);
        if (c == null){
            throw new ServiceException("修改失败，请检查参数");
        }
        classesDao.delete(id);
    }

    public String importFile(File file) throws ServiceException {
        HSSFWorkbook workbook = null;
        StringBuffer msg = new StringBuffer();
        try{
            workbook = new HSSFWorkbook(new FileInputStream(file));
            HSSFSheet sheet = workbook.getSheetAt(0);
            int cont = sheet.getLastRowNum()+1;
            Classes c = null;
            Teacher t = null;
            int successCount = 0;
            int errorCount = 0;
            for (int i = 1; i < cont; i++){
                try{
                    c = new Classes();
                    c.setName(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(0)));
                    c.setNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(1)));
                    c.setGrade(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(2)));
                    c.setState(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(3)));
                    t = (Teacher) teacherDao.getByNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(4)));
                    if (t != null){
                        c.setTeacher(t);
                    }
                    classesDao.add(c);
                    successCount++;
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
                msg.insert(0,"文件上传成功<br/>导入成功:"+ successCount +"条" + "<br/>导入失败:"+errorCount+ "条 <br/>记录<br/>");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("导入失败，解析文件错误，请检查文件格式");
        }
        return msg.toString();
    }

    public void exportFile(OutputStream os) throws Exception {
        List<Classes> classes = classesDao.getAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow rows = sheet.createRow(0);
        rows.createCell(0).setCellValue("班级名称");
        rows.createCell(1).setCellValue("班级编号");
        rows.createCell(2).setCellValue("年级");
        rows.createCell(3).setCellValue("状态");
        rows.createCell(4).setCellValue("班主任姓名");
        rows.createCell(5).setCellValue("班主任电话");
        for (int i = 0; i < classes.size(); i++){
            rows = sheet.createRow(i+1);
            rows.createCell(0).setCellValue(classes.get(i).getName());
            rows.createCell(1).setCellValue(classes.get(i).getNumber());
            rows.createCell(2).setCellValue(classes.get(i).getGrade());
            rows.createCell(3).setCellValue(classes.get(i).getState());
            if (classes.get(i).getTeacher() != null){
                rows.createCell(4).setCellValue(classes.get(i).getTeacher().getName());
                rows.createCell(5).setCellValue(classes.get(i).getTeacher().getPhone());
            }
        }
        workbook.write(os);
    }


}
