package cloudoer.su.dao.impl;

import cloudoer.su.base.impl.BaseDaoImpl;
import cloudoer.su.dao.StudentDao;
import cloudoer.su.entity.Student;
import org.springframework.stereotype.Repository;

@Repository("studentDao")
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao<Student> {
}
