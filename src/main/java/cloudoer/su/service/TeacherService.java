package cloudoer.su.service;

import cloudoer.su.base.BaseService;
import cloudoer.su.entity.Teacher;
import cloudoer.su.exception.ServiceException;

import java.io.File;
import java.util.List;

public interface TeacherService extends BaseService {
    List<Teacher> getAll();

    List<Teacher> getByPage(int pageNo, int pageSize);

    Teacher getById(String id);

    Teacher getByNumber(String number);

    String add(Teacher teacher);

    void update (Teacher teacher);

    void delete (String id);

    String importFile(File file) throws ServiceException;
}
