package cloudoer.su.service;

import cloudoer.su.base.BaseService;
import cloudoer.su.entity.Dormitory;
import cloudoer.su.entity.Student;
import cloudoer.su.exception.ServiceException;

import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

public interface DormitoryService extends BaseService {
    List<Dormitory> getAll();

    List<Dormitory> getByPage(int pageNo, int pageSize);

    Dormitory getById(String id);

    Dormitory getByNumber(String number);

    Set<Student> getStudents(String id);

    String add(Dormitory dormitory);

    void update (Dormitory dormitory, String studentId);

    void delete (String id);

    String importFile(File file) throws ServiceException;

    void exportFile(OutputStream os)throws Exception;
}
