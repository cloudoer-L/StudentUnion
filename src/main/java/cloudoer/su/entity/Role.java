package cloudoer.su.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 部门职务，角色
 * Created by cloudoer on 2018/2/2.
 */
@Entity
@Table(name = "su_role")
public class Role implements Serializable {
    @Transient
    private final int OFFSETVALUE = 86;

    @Id
    @GenericGenerator(name="su_uuid", strategy = "uuid")
    @GeneratedValue(generator = "su_uuid")
    private String id;
    private String name;
    private String number;
    private String introduce;//职务、角色说明
    private String state;

    @ManyToMany(targetEntity = Menu.class)
    @JoinTable(name = "su_role_menu", joinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "menuId", referencedColumnName = "id"))
    private Set<Menu> menus = new HashSet<Menu>();
    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "su_user_role", joinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"))
    private Set<User> users = new HashSet<User>();

    public Role(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", introduce='" + introduce + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return number.equals(role.number);

    }

    @Override
    public int hashCode() {
        return number.hashCode() * OFFSETVALUE;
    }
}
