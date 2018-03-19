package cloudoer.su.service.impl;

import cloudoer.su.base.impl.BaseServiceImpl;
import cloudoer.su.entity.Menu;
import cloudoer.su.exception.ServiceException;
import cloudoer.su.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("menuService")
@Transactional
public class MenuServiceImpl extends BaseServiceImpl implements MenuService {


    public List<Menu> getAll() {
        List<Menu> list = menuDao.getAll();
        List<Menu> menus = new ArrayList<Menu>();
        for (Menu menu : list){
            if (menu.getSuperior() != null){
                menu.set_parentId(menu.getSuperior().getId());
            }
            menus.add(menu);
        }
        return menus;
    }

    public List<Menu> getAllByGrade() {
        List<Menu> list = menuDao.getAllByGrade();
        return list;
    }

    public Menu getById(String id) {
        return (Menu) menuDao.getById(id);
    }

    public String add(Menu menu, String id){
        if (id == null || "".equals(id)){
            menu.setGrade("1");
            String t = menuDao.add(menu);
            return t;
        }else {
            Menu superiorMenu = (Menu) menuDao.getById(id);
            int grade = Integer.parseInt(superiorMenu.getGrade())+1;
            menu.setSuperior(superiorMenu);
            menu.setGrade(String.valueOf(grade));
            return menuDao.add(menu);
        }
    }

    public void update(Menu menu) throws ServiceException{
        Menu m = (Menu) menuDao.getById(menu.getId());
        if (m == null){
            throw new ServiceException("修改失败，请检查参数");
        }
        m.setName(menu.getName());
        m.setValue(menu.getValue());
        m.setNumber(menu.getNumber());
        m.setUrl(menu.getUrl());
        m.setIco(menu.getIco());
        m.setIntroduce(menu.getIntroduce());
        m.setState(menu.getState());
    }

    public void delete(String id) throws ServiceException {
        Menu m = (Menu) menuDao.getById(id);
        if (m == null){
            throw new ServiceException("删除失败，请检查参数");
        }
        delete(m);
    }

    /**
     * 递归删除所有下级菜单和本菜单
     * @param menu
     */
    private void delete (Menu menu){
        if (menu.getLower() == null || menu.getLower().size() == 0){
            menuDao.delete(menu.getId());
        }else {
            for (Menu m : menu.getLower()){
                delete(m);
            }
            menuDao.delete(menu.getId());
        }
    }

}
