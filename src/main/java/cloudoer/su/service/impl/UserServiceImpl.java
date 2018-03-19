package cloudoer.su.service.impl;

import cloudoer.su.base.impl.BaseServiceImpl;
import cloudoer.su.entity.User;
import cloudoer.su.service.TeacherService;
import cloudoer.su.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements UserService {


    public User login(User user) {
        return userDao.login(user);
    }
}
