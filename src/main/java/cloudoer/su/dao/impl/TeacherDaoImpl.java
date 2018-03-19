package cloudoer.su.dao.impl;

import cloudoer.su.base.impl.BaseDaoImpl;
import cloudoer.su.dao.TeacherDao;
import cloudoer.su.entity.Teacher;
import cloudoer.su.exception.ServiceException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;

@Repository("teacherDao")
public class TeacherDaoImpl extends BaseDaoImpl<Teacher> implements TeacherDao<Teacher> {
    public String importFile(File file) throws ServiceException{
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
                    t.setName(sheet.getRow(i).getCell(0).toString());
                    t.setNumber(sheet.getRow(i).getCell(1).toString());
                    t.setSex(sheet.getRow(i).getCell(2).toString());
                    t.setIdCard(sheet.getRow(i).getCell(3).toString());
                    t.setPhone(sheet.getRow(i).getCell(4).toString());
                    t.setQq(sheet.getRow(i).getCell(5).toString());
                    t.setEmail(sheet.getRow(i).getCell(6).toString());
                    t.setState(sheet.getRow(i).getCell(7).toString());
                    getSession().save(t);
                    successCount ++ ;
                    if (i+1%20==0){
                        getSession().flush();
                        getSession().clear();
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
}
