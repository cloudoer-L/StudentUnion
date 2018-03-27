package cloudoer.su.service.impl;

import cloudoer.su.base.impl.BaseServiceImpl;
import cloudoer.su.entity.Dormitory;
import cloudoer.su.entity.Student;
import cloudoer.su.exception.ServiceException;
import cloudoer.su.service.DormitoryService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("dormitoryService")
@Transactional
public class DormitoryServiceImpl extends BaseServiceImpl implements DormitoryService {
    public List<Dormitory> getAll() {
        return dormitoryDao.getAll();
    }

    public List<Dormitory> getByPage(int pageNo, int pageSize) {
        return dormitoryDao.getByPage(pageNo,pageSize);
    }

    public Dormitory getById(String id) {
        return (Dormitory) dormitoryDao.getById(id);
    }

    public Dormitory getByNumber(String number) {
        return (Dormitory) dormitoryDao.getByNumber(number);
    }

    public Set<Student> getStudents(String id) {
        Dormitory d = (Dormitory) dormitoryDao.getById(id);
        if (d.getStudents() == null){
            return new HashSet<Student>();
        }
        return d.getStudents();
    }

    public String add(Dormitory dormitory) {
        return dormitoryDao.add(dormitory);
    }

    public void update(Dormitory dormitory, String studentId) {
        Dormitory d = (Dormitory) dormitoryDao.getById(dormitory.getId());
        if (d == null){
            throw new ServiceException("修改失败，请检查参数");
        }
        if (studentId != null && "".equals(studentId)){
            d.setAdmin((Student) studentDao.getById(studentId));
        }
        d.setName(dormitory.getName());
        d.setNumber(dormitory.getNumber());
        d.setBuildingName(dormitory.getBuildingName());
        d.setBuildingNumber(dormitory.getBuildingNumber());
        d.setCapacity(dormitory.getCapacity());
        d.setAlreadyCapacity(dormitory.getAlreadyCapacity());
        d.setState(dormitory.getState());
    }

    public void delete(String id) {
        Dormitory d = (Dormitory) dormitoryDao.getById(id);
        if (d == null){
            throw new ServiceException("删除失败，请检查参数");
        }
        dormitoryDao.delete(id);
    }

    public String importFile(File file) throws ServiceException {
        HSSFWorkbook workbook = null;
        StringBuffer msg = new StringBuffer();
        try {
            workbook = new HSSFWorkbook(new FileInputStream(file));
            HSSFSheet sheet = workbook.getSheetAt(0);
            int cont = sheet.getLastRowNum()+1;
            Dormitory d = null;
            int successCount = 0;
            int errorCount = 0;
            for (int i = 1; i < cont; i++){
                try {
                    d = new Dormitory();
                    d.setName(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(0)));
                    d.setBuildingName(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(1)));
                    d.setBuildingNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(2)));
                    d.setNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(3)));
                    d.setCapacity(Integer.parseInt(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(4))));
                    d.setState(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(5)));
                    dormitoryDao.add(d);
                    successCount ++ ;
                    if (i+1%20==0){
                        dormitoryDao.getSession().flush();
                        dormitoryDao.getSession().clear();
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
        List<Dormitory> dormitories = dormitoryDao.getAll();
        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet1");
        //创建一行
        HSSFRow rows = sheet.createRow(0);
        //创建这一行的一个单元格
        rows.createCell(0).setCellValue("寝室名称");
        rows.createCell(1).setCellValue("寝室搂名");
        rows.createCell(2).setCellValue("寝室门牌号");
        rows.createCell(3).setCellValue("寝室编号");
        rows.createCell(4).setCellValue("总床位数");
        rows.createCell(5).setCellValue("已住人数");
        rows.createCell(6).setCellValue("状态");
        for (int i = 0; i < dormitories.size(); i++){
            rows = sheet.createRow(i+1);
            rows.createCell(0).setCellValue(dormitories.get(i).getName());
            rows.createCell(1).setCellValue(dormitories.get(i).getBuildingName());
            rows.createCell(2).setCellValue(dormitories.get(i).getBuildingNumber());
            rows.createCell(3).setCellValue(dormitories.get(i).getNumber());
            rows.createCell(4).setCellValue(dormitories.get(i).getCapacity());
            rows.createCell(5).setCellValue(dormitories.get(i).getAlreadyCapacity() == null?0:dormitories.get(i).getAlreadyCapacity());
            rows.createCell(6).setCellValue(dormitories.get(i).getState());
        }
        workbook.write(os);
    }

    public void appointAdmin(String id, String adminId){
        Dormitory d = (Dormitory) dormitoryDao.getById(id);
        if (d == null){
            throw new ServiceException("设置失败，请寝室参数");
        }
        Student s = (Student) studentDao.getById(adminId);
        if (s == null){
            throw new ServiceException("设置失败，请学生参数");
        }
        d.setAdmin(s);
    }
}
