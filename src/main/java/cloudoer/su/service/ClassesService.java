package cloudoer.su.service;

import cloudoer.su.base.BaseService;
import cloudoer.su.entity.ClassCommittee;
import cloudoer.su.entity.Classes;
import cloudoer.su.entity.Student;
import cloudoer.su.exception.ServiceException;

import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

public interface ClassesService extends BaseService {
    List<Classes> getAll();

    List<Classes> getByPage(int pageNo, int pageSize);

    Classes getById(String id);

    Classes getByNumber(String number);

    String add(Classes classes, String teacherId);

    void update (Classes classes, String teacherId);

    void delete (String id);

    String importFile(File file) throws ServiceException;

    void exportFile(OutputStream os)throws Exception;

    /**
     * 获取班级学生
     * @param id
     * @return
     */
    Set<Student> getStudents(String id);

    /**
     * 获取所有班委
     * @param id
     * @return
     */
    Set<ClassCommittee> getClassCommittee(String id);
}
