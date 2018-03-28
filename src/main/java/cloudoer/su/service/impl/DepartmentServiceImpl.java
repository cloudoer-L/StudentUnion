package cloudoer.su.service.impl;

import cloudoer.su.base.impl.BaseServiceImpl;
import cloudoer.su.entity.Department;
import cloudoer.su.entity.Member;
import cloudoer.su.exception.ServiceException;
import cloudoer.su.service.DepartmentService;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

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

    public String add(Department department, String leaderId, String superiorId) {
        Member leader = (Member) memberDao.getById(leaderId);
        if (leader != null){
            department.setLeader(leader);
        }
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
        return null;
    }

    public void exportFile(OutputStream os) throws Exception {

    }
}
