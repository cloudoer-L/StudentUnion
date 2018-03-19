package cloudoer.su.service;

import cloudoer.su.base.BaseService;
import cloudoer.su.entity.User;

public interface UserService extends BaseService {

    User login(User user);
}
