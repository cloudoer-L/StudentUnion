package cloudoer.su.base.impl;

import cloudoer.su.base.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    @Resource(name = "sessionFactory")
    private SessionFactory factory;

    private Class<T> clazz = null;;

    public BaseDaoImpl() {
        //使用反射技术得到T的真实类型
        //获取当前新建对象的泛型的父类
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取第一个类型参数的真实类型
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }

    /**
     * 获取session
     * @return
     */
    protected Session getSession(){
        return factory.getCurrentSession();
        //return factory.openSession();
    }

    public String add(T t) {
        return getSession().save(t).toString();
    }

    public void update(T t) {
        getSession().saveOrUpdate(t);
    }

    public void delete(String id) {
        T t = getById(id);
        if (t != null){
            getSession().delete(t);
        }
    }

    public List<T> getAll() {
        return getSession().createQuery("from " + clazz.getSimpleName() + " t order by t.number").list();
    }

    public List<T> getByPage(int pageNo, int pageSize) {
        return getSession().createQuery("from " + clazz.getSimpleName())
                .setFirstResult((pageNo - 1) * pageSize)
                .setMaxResults(pageSize)
                .list();
    }

    public T getById(String id) {
        return (T) getSession().get(clazz, id);
    }

    public T getByNumber(String number) {
        String hql = "from " + clazz.getSimpleName() + " t where t.number = ?";
        List<T> list = getSession().createQuery(hql)
                .setString(0, number)
                .list();
        if (list == null || list.size() == 0){
            return null;
        }else {
            return list.get(0);
        }
    }

    public int getCount() {
        return ((Number)getSession().createQuery("select count(id) from " + clazz.getSimpleName())
                .uniqueResult())
                .intValue();
    }

    public String importFile(File file) {
        return "这个模块不允许导入";
    }
}
