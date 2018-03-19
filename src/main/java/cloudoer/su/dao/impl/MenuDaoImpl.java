package cloudoer.su.dao.impl;

import cloudoer.su.base.impl.BaseDaoImpl;
import cloudoer.su.dao.ClassesDao;
import cloudoer.su.dao.MenuDao;
import cloudoer.su.entity.Classes;
import cloudoer.su.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *
 */
@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao<Menu> {


    public List<Menu> getAllByGrade() {
        return getSession().createQuery("from Menu m where m.grade=1 order by m.number").list();
    }

}
