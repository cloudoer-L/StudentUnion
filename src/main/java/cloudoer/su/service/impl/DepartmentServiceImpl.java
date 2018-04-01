package cloudoer.su.service.impl;

import cloudoer.su.base.impl.BaseServiceImpl;
import cloudoer.su.entity.Department;
import cloudoer.su.entity.Member;
import cloudoer.su.exception.ServiceException;
import cloudoer.su.service.DepartmentService;
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

@Service("departmentService")
@Transactional
public class DepartmentServiceImpl extends BaseServiceImpl implements DepartmentService {
    public List<Department> getAll() {
        return departmentDao.getAll();
    }

    public List<Department> getByPage(int pageNo, int pageSize) {
        return departmentDao.getByPage(pageNo,pageSize);
    }

    public Department getById(String id) {
        return (Department) departmentDao.getById(id);
    }

    public Department getByNumber(String number) {
        return (Department) departmentDao.getByNumber(number);
    }

    public String add(Department department, String superiorId) {
        Department superior = (Department) departmentDao.getById(superiorId);
        if (superior != null){
            department.setSuperior(superior);
        }
        return departmentDao.add(department);
    }

    public void update(Department department, String leaderId, String superiorId) {
        Department d = (Department) departmentDao.getById(department.getId());
        if (d == null){
            throw new ServiceException("修改失败，参数错误");
        }
        Member leader = (Member) memberDao.getById(leaderId);
        if (leader != null){
            d.setLeader(leader);
        }
        Department superior = (Department) departmentDao.getById(superiorId);
        if (superior != null){
            d.setSuperior(superior);
        }
        d.setName(department.getName());
        d.setNumber(department.getNumber());
        d.setState(department.getState());
        d.setIntroduce(department.getIntroduce());
    }

    public void delete(String id) {
        Department d = (Department) departmentDao.getById(id);
        if (d == null){
            throw new ServiceException("删除失败，参数错误");
        }
        departmentDao.delete(id);
    }

    public String importFile(File file) throws ServiceException {
        HSSFWorkbook workbook = null;
        StringBuffer msg = new StringBuffer();
        try {
            workbook = new HSSFWorkbook(new FileInputStream(file));
            HSSFSheet sheet = workbook.getSheetAt(0);
            int cont = sheet.getLastRowNum()+1;
            Department d = null;
            Department superior = null;
            Member leader = null;
            int successCount = 0;
            int errorCount = 0;
            for (int i = 1; i < cont; i++){
                try {
                    d = new Department();
                    d.setName(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(0)));
                    d.setNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(1)));
                    d.setIntroduce(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(2)));
                    d.setState(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(3)));
                    superior = (Department) departmentDao.getByNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(4)));
                    leader = (Member) memberDao.getByNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(5)));
                    if (superior != null){
                        d.setSuperior(superior);
                    }
                    if (leader != null){
                        d.setLeader(leader);
                    }
                    departmentDao.add(d);
                    successCount ++ ;
                    if (i+1%20==0){
                        departmentDao.getSession().flush();
                        departmentDao.getSession().clear();
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
        List<Department> teachers = departmentDao.getAll();
        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet1");
        //创建一行
        HSSFRow rows = sheet.createRow(0);
        //创建这一行的一个单元格
        rows.createCell(0).setCellValue("姓名");
        rows.createCell(1).setCellValue("教师编号");
        rows.createCell(2).setCellValue("部门介绍");
        rows.createCell(3).setCellValue("状态");
        rows.createCell(4).setCellValue("上级部门编号");
        rows.createCell(5).setCellValue("上级部门名称");
        rows.createCell(6).setCellValue("负责人编号");
        rows.createCell(7).setCellValue("负责人姓名");
        for (int i = 0; i < teachers.size(); i++){
            rows = sheet.createRow(i+1);
            rows.createCell(0).setCellValue(teachers.get(i).getName());
            rows.createCell(1).setCellValue(teachers.get(i).getNumber());
            rows.createCell(2).setCellValue(teachers.get(i).getIntroduce());
            rows.createCell(3).setCellValue(teachers.get(i).getState());
            if (teachers.get(i).getSuperior() != null){
                rows.createCell(4).setCellValue(teachers.get(i).getSuperior().getNumber());
                rows.createCell(5).setCellValue(teachers.get(i).getSuperior().getName());
            }
            if (teachers.get(i).getLeader() != null){
                rows.createCell(6).setCellValue(teachers.get(i).getLeader().getNumber());
                rows.createCell(7).setCellValue(teachers.get(i).getLeader().getName());
            }
        }
        workbook.write(os);
    }

    public Set<Member> getMembers(String id){
        Department d = (Department) departmentDao.getById(id);
        if (d == null){
            throw new ServiceException("参数错误");
        }
        if (d.getMembers() == null){
            return new HashSet<Member>();
        }
        return d.getMembers();
    }
}
