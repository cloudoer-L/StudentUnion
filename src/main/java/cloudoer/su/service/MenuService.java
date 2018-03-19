package cloudoer.su.service;

import cloudoer.su.base.BaseService;
import cloudoer.su.entity.Menu;
import cloudoer.su.exception.ServiceException;

import java.util.List;

public interface MenuService extends BaseService {

    List<Menu> getAll();

    List<Menu> getAllByGrade();

    Menu getById(String id);

    String add(Menu menu, String id);

    void update(Menu menu) throws ServiceException;

    void delete (String id) throws ServiceException;
}
