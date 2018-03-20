package cloudoer.su.service.impl;

import cloudoer.su.base.impl.BaseServiceImpl;
import cloudoer.su.entity.Classes;
import cloudoer.su.entity.Teacher;
import cloudoer.su.exception.ServiceException;
import cloudoer.su.service.ClassesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

@Service("classesService")
@Transactional
public class ClassesServiceImpl extends BaseServiceImpl implements ClassesService {
    public List<Classes> getAll() {
        return classesDao.getAll();
    }

    public List<Classes> getByPage(int pageNo, int pageSize) {
        return classesDao.getByPage(pageNo, pageSize);
    }

    public Classes getById(String id) {
        return (Classes) classesDao.getById(id);
    }

    public Classes getByNumber(String number) {
        return (Classes) classesDao.getByNumber(number);
    }

    public String add(Classes classes, String teacherId) {
        Teacher teacher = (Teacher) teacherDao.getById(teacherId);
        classes.setTeacher(teacher);
        return classesDao.add(classes);
    }

    public void update(Classes classes, String teacherId) {
        Classes c = (Classes) classesDao.getById(classes.getId());
        if (c == null){
            throw new ServiceException("修改失败，请检查参数");
        }
        c.setName(classes.getName());
        c.setNumber(classes.getNumber());
        c.setGrade(classes.getGrade());
        c.setState(classes.getState());
    }

    public void delete(String id) {
        Classes c = (Classes) classesDao.getById(id);
        if (c == null){
            throw new ServiceException("修改失败，请检查参数");
        }
        classesDao.delete(id);
    }

    public String importFile(File file) throws ServiceException {
        return null;
    }

    public void exportFile(OutputStream os) throws Exception {

    }


}
