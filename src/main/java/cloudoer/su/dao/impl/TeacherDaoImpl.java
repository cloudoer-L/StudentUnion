package cloudoer.su.dao.impl;

import cloudoer.su.base.impl.BaseDaoImpl;
import cloudoer.su.dao.TeacherDao;
import cloudoer.su.entity.Teacher;
import cloudoer.su.exception.ServiceException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Repository("teacherDao")
public class TeacherDaoImpl extends BaseDaoImpl<Teacher> implements TeacherDao<Teacher> {

}
