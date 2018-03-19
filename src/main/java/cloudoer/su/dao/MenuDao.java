package cloudoer.su.dao;

import cloudoer.su.base.BaseDao;
import cloudoer.su.entity.Menu;

import java.util.List;

public interface MenuDao<T> extends BaseDao<T> {
    /**
     * 分类获取菜单（树形结构）
     * @return
     */
    List<Menu> getAllByGrade();
}
