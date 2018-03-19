package cloudoer.su.dao.impl;

import cloudoer.su.base.impl.BaseDaoImpl;
import cloudoer.su.dao.PositionDao;
import cloudoer.su.entity.Position;
import org.springframework.stereotype.Repository;

@Repository("positionDao")
public class PositionDaoImpl extends BaseDaoImpl<Position> implements PositionDao<Position> {
}
