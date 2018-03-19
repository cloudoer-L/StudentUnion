package cloudoer.su.base;

import java.io.File;
import java.util.List;

public interface BaseDao<T> {
    /**
     * 添加
     * @param t
     * @return
     */
    String add(T t);

    /**
     * 修改
     * @param t
     */
    void update(T t);

    /**
     * 删除
     * @param id
     */
    void delete (String id);

    /**
     * 获取全部
     * @return
     */
    List<T> getAll();

    /**
     * 分页获取
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<T> getByPage(int pageNo, int pageSize);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    T getById(String id);

    /**
     * 根据number获取
     * @param number
     * @return
     */
    T getByNumber(String number);

    /**
     * 获取总记录数
     * @return
     */
    int getCount();

    String importFile(File file);
}
