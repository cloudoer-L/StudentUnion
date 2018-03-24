package cloudoer.su.service;

import cloudoer.su.base.BaseService;
import cloudoer.su.entity.Student;
import cloudoer.su.exception.ServiceException;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

public interface StudentService extends BaseService {
    List<Student> getAll();

    List<Student> getByPage(int pageNo, int pageSize);

    Student getById(String id);

    Student getByNumber(String number);

    String add(Student student, String classesId, String dormitoryId) throws Exception;

    void update (Student student, String classesId, String dormitoryId) throws Exception;

    void delete (String id);

    String importFile(File file) throws ServiceException;

    void exportFile(OutputStream os)throws Exception;
}
