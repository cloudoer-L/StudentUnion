package cloudoer.su.utils;

import cloudoer.su.entity.Menu;
import cloudoer.su.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class InstallSystem {

    private ApplicationContext ac = new FileSystemXmlApplicationContext("bean-base.xml");
    private SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");

    /**
     * 创建超级管理员
     */
    public void adminInit(){
        User user = new User();
        user.setUserName("admin");
        user.setPassword("123456");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    /**
     * 创建一些菜单
     */
    public void menuInit(){
        Menu menu1 = new Menu();
        menu1.setName("开发人员选项");
        menu1.setValue("developer");
        menu1.setNumber("010000000");
        Menu menu2 = new Menu();
        menu2.setName("菜单管理");
        menu2.setValue("developer_menu");
        menu2.setNumber("010010000");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(menu1);
        menu2.setSuperior(menu1);
        session.save(menu2);
        transaction.commit();
        session.close();
    }

    public static void main(String[] arge){
        InstallSystem installSystem = new InstallSystem();
        //installSystem.adminInit();
        installSystem.menuInit();
    }

}
