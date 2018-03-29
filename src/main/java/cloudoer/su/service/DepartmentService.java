package cloudoer.su.service;

import cloudoer.su.base.BaseService;
import cloudoer.su.entity.Department;
import cloudoer.su.entity.Member;
import cloudoer.su.exception.ServiceException;

import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

public interface DepartmentService extends BaseService {
    List<Department> getAll();

    List<Department> getByPage(int pageNo, int pageSize);

    Department getById(String id);

    Department getByNumber(String number);

    String add(Department department, String superiorId);

    void update (Department department, String leaderId, String superiorId);

    void delete (String id);

    String importFile(File file) throws ServiceException;

    void exportFile(OutputStream os)throws Exception;

    Set<Member> getMembers(String id);
}
