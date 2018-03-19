package cloudoer.su.service.impl;

import cloudoer.su.entity.Teacher;
import cloudoer.su.exception.ServiceException;
import cloudoer.su.service.TeacherService;
import cloudoer.su.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

@Service("teacherService")
@Transactional
public class TeacherServiceImpl extends BaseServiceImpl implements TeacherService {
    public List<Teacher> getAll (){
        return teacherDao.getAll();
    }

    public List<Teacher> getByPage(int pageNo, int pageSize){
        return teacherDao.getByPage(pageNo, pageSize);
    }

    public Teacher getById(String id){
        return (Teacher) teacherDao.getById(id);
    }

    public Teacher getByNumber(String number){
        return (Teacher) teacherDao.getByNumber(number);
    }

    public String add(Teacher teacher){
        return teacherDao.add(teacher);
    }

    public void update(Teacher teacher){
        Teacher t = (Teacher) teacherDao.getById(teacher.getId());
        if (t == null){
            throw new ServiceException("修改失败，请检查参数");
        }
        t.setName(teacher.getName());
        t.setNumber(teacher.getNumber());
        t.setSex(teacher.getSex());
        t.setIdCard(teacher.getIdCard());
        t.setPhone(teacher.getPhone());
        t.setQq(teacher.getQq());
        t.setEmail(teacher.getEmail());
        t.setState(teacher.getState());
    }

    public void delete (String id){
        Teacher t = (Teacher) teacherDao.getById(id);
        if (t == null){
            throw new ServiceException("删除失败，请检查参数");
        }
        teacherDao.delete(id);
    }

    public String importFile(File file) throws ServiceException {
        return teacherDao.importFile(file);
    }

    public void exportFile(OutputStream os) throws Exception {
        teacherDao.exportFile(teacherDao.getAll(), os);
    }


}
