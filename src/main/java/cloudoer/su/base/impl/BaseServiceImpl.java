package cloudoer.su.base.impl;

import cloudoer.su.base.BaseService;
import cloudoer.su.dao.*;
import cloudoer.su.entity.Classes;

import javax.annotation.Resource;

public abstract class BaseServiceImpl implements BaseService {
    @Resource(name = "classesDao")
    protected ClassesDao classesDao;

    @Resource(name = "departmentDao")
    protected DepartmentDao departmentDao;

    @Resource(name = "dormitoryDao")
    protected DormitoryDao dormitoryDao;

    @Resource(name = "memberDao")
    protected MemberDao memberDao;

    @Resource(name = "menuDao")
    protected MenuDao menuDao;

    @Resource(name = "personDao")
    protected PersonDao personDao;

    @Resource(name = "positionDao")
    protected PositionDao positionDao;

    @Resource(name = "roleDao")
    protected RoleDao roleDao;

    @Resource(name = "studentDao")
    protected StudentDao studentDao;

    @Resource(name = "teacherDao")
    protected TeacherDao teacherDao;

    @Resource(name = "userDao")
    protected UserDao userDao;
}
