package cloudoer.su.dao.impl;

import cloudoer.su.base.impl.BaseDaoImpl;
import cloudoer.su.dao.UserDao;
import cloudoer.su.entity.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao<User> {

    /**
     * 用户没有number字段，在此重写baseDao的getByNumber方法
     * @param number
     * @return
     */
    public User getByNumber(String number){
        return null;
    }

    public User login(User user) {
        String queryString = "from User where userName=? and password=?";
        User user0 = null;
        Query queryObject = getSession().createQuery(queryString);
        queryObject.setString(0, user.getUserName());
        queryObject.setString(1, user.getPassword());
        List<User> list = queryObject.list();
        if(list != null && list.size() > 0){
            user0 = list.get(0);
        }
        return user0;
    }
}
