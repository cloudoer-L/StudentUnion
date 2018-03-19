package cloudoer.su.dao.impl;

import cloudoer.su.base.impl.BaseDaoImpl;
import cloudoer.su.dao.DormitoryDao;
import cloudoer.su.entity.Dormitory;
import org.springframework.stereotype.Repository;

@Repository("dormitoryDao")
public class DormitoryDaoImpl extends BaseDaoImpl<Dormitory> implements DormitoryDao<Dormitory> {
}
