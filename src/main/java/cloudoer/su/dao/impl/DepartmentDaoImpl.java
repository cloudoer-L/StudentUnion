package cloudoer.su.dao.impl;

import cloudoer.su.base.impl.BaseDaoImpl;
import cloudoer.su.dao.DepartmentDao;
import cloudoer.su.entity.Department;
import org.springframework.stereotype.Repository;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao<Department> {
}
