package cloudoer.su.dao.impl;

import cloudoer.su.base.impl.BaseDaoImpl;
import cloudoer.su.dao.ClassCommitteeDao;
import cloudoer.su.dao.ClassesDao;
import cloudoer.su.entity.ClassCommittee;
import cloudoer.su.entity.Classes;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *
 */
@Repository("classCommitteeDao")
public class ClassCommitteeDaoImpl extends BaseDaoImpl<ClassCommittee> implements ClassCommitteeDao<ClassCommittee> {
    /**
     * 班委没有number字段，在此重写baseDao的getByNumber方法
     * @param number
     * @return
     */
    public ClassCommittee getByNumber(String number){
        return null;
    }

    /**
     * 班委需要按职务编号排序
     * @return
     */
    public List<ClassCommittee> getAll(){
        return getSession().createQuery("from ClassCommittee t order by t.positionNumber").list();
    }

    /**
     * 班委需要按职务编号排序
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<ClassCommittee> getByPage(int pageNo, int pageSize) {
        return getSession().createQuery("from ClassCommittee t order by t.positionNumber")
                .setFirstResult((pageNo - 1) * pageSize)
                .setMaxResults(pageSize)
                .list();
    }
}
