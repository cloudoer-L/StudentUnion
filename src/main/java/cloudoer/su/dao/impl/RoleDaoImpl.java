package cloudoer.su.dao.impl;

import cloudoer.su.base.impl.BaseDaoImpl;
import cloudoer.su.dao.RoleDao;
import cloudoer.su.entity.Role;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao<Role> {
}
