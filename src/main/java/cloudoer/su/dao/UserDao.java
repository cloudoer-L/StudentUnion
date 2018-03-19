package cloudoer.su.dao;

import cloudoer.su.base.BaseDao;
import cloudoer.su.entity.User;

public interface UserDao<T> extends BaseDao<T> {
    T getByNumber(String number);

    User login(User user);
}
