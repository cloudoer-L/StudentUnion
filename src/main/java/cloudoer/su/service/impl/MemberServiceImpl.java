package cloudoer.su.service.impl;

import cloudoer.su.base.impl.BaseServiceImpl;
import cloudoer.su.entity.Department;
import cloudoer.su.entity.Member;
import cloudoer.su.entity.Person;
import cloudoer.su.exception.ServiceException;
import cloudoer.su.service.MemberService;
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

@Service("memberService")
@Transactional
public class MemberServiceImpl extends BaseServiceImpl implements MemberService {
    public List<Member> getAll() {
        return memberDao.getAll();
    }

    public List<Member> getByPage(int pageNo, int pageSize) {
        return memberDao.getByPage(pageNo, pageSize);
    }

    public Member getById(String id) {
        return (Member) memberDao.getById(id);
    }

    public Member getByNumber(String number) {
        return (Member) memberDao.getByNumber(number);
    }

    public String add(Member member,String personNumber, String departmentNumber) {
        Person p = (Person) personDao.getByNumber(personNumber);
        Department d= (Department) departmentDao.getByNumber(departmentNumber);
        if (p == null || d == null){
            throw new ServiceException("添加失败，参数错误");
        }
        member.setName(p.getName());
        member.setDepartment(d);
        member.setPerson(p);
        return memberDao.add(member);
    }

    public void update(Member member,String personNumber, String departmentNumber) {
        Member m = (Member) memberDao.getById(member.getId());
        Person p = (Person) personDao.getByNumber(personNumber);
        Department d= (Department) departmentDao.getByNumber(departmentNumber);
        if (m == null || p == null || d == null){
            throw new ServiceException("修改失败，参数错误");
        }
        member.setDepartment(d);
        member.setPerson(p);
        m.setName(p.getName());
        m.setNumber(member.getNumber());
        m.setPlace(member.getPlace());
        m.setState(member.getState());
    }

    public void delete(String id) {
        Member m = (Member) memberDao.getById(id);
        if (m == null){
            throw new ServiceException("删除失败，参数错误");
        }
        memberDao.delete(id);
    }

    public String importFile(File file) throws ServiceException {
        HSSFWorkbook workbook = null;
        StringBuffer msg = new StringBuffer();
        try{
            workbook = new HSSFWorkbook(new FileInputStream(file));
            HSSFSheet sheet = workbook.getSheetAt(0);
            int cont = sheet.getLastRowNum()+1;
            Person p = null;
            Department d = null;
            Member m = null;
            int successCount = 0;
            int errorCount = 0;
            for (int i = 1; i < cont; i++){
                try{
                    m = new Member();
                    m.setNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(0)));
                    m.setPlace(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(1)));
                    m.setState(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(2)));
                    p = (Person) personDao.getByNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(3)));
                    d = (Department) departmentDao.getByNumber(ExcelUtil.getCallValueToString(sheet.getRow(i).getCell(4)));
                    if (p == null || d == null){
                        throw new ServiceException("学号或部门编号错误");
                    }
                    m.setPerson(p);
                    m.setDepartment(d);
                    m.setName(p.getName());
                    memberDao.add(m);
                    successCount++;
                    if (i+1%20==0){
                        memberDao.getSession().flush();
                        memberDao.getSession().clear();
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
        List<Member> list = memberDao.getAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow rows = sheet.createRow(0);
        rows.createCell(0).setCellValue("姓名");
        rows.createCell(1).setCellValue("编号");
        rows.createCell(2).setCellValue("电话");
        rows.createCell(3).setCellValue("QQ");
        rows.createCell(4).setCellValue("所在部门");
        rows.createCell(5).setCellValue("担任职务");
        rows.createCell(6).setCellValue("状态");
        for (int i = 0; i < list.size(); i++){
            rows = sheet.createRow(i+1);
            rows.createCell(0).setCellValue(list.get(i).getName());
            rows.createCell(1).setCellValue(list.get(i).getNumber());
            if (list.get(i).getPerson() != null){
                rows.createCell(2).setCellValue(list.get(i).getPerson().getPhone());
                rows.createCell(3).setCellValue(list.get(i).getPerson().getQq());
            }
            if (list.get(i).getDepartment() != null){
                rows.createCell(4).setCellValue(list.get(i).getDepartment().getName());
            }
            rows.createCell(5).setCellValue(list.get(i).getPlace());
            rows.createCell(6).setCellValue(list.get(i).getState());
        }
        workbook.write(os);
    }
}
