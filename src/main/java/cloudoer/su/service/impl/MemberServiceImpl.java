package cloudoer.su.service.impl;

import cloudoer.su.base.impl.BaseServiceImpl;
import cloudoer.su.entity.Department;
import cloudoer.su.entity.Member;
import cloudoer.su.entity.Person;
import cloudoer.su.exception.ServiceException;
import cloudoer.su.service.MemberService;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

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
        Department d= (Department) dormitoryDao.getByNumber(departmentNumber);
        if (p == null || d == null){
            throw new ServiceException("添加失败，参数错误");
        }
        member.setDepartment(d);
        member.setPerson(p);
        return memberDao.add(member);
    }

    public void update(Member member,String personNumber, String departmentNumber) {
        Member m = (Member) memberDao.getById(member.getId());
        Person p = (Person) personDao.getByNumber(personNumber);
        Department d= (Department) dormitoryDao.getByNumber(departmentNumber);
        if (m == null || p == null || d == null){
            throw new ServiceException("修改失败，参数错误");
        }
        member.setDepartment(d);
        member.setPerson(p);
        m.setName(member.getName());
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
        return null;
    }

    public void exportFile(OutputStream os) throws Exception {

    }
}
