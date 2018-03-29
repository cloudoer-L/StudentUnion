package cloudoer.su.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户
 * Created by cloudoer on 2018/2/2.
 */
@Entity
@Table(name = "su_user")
public class User implements Serializable {
    @Transient
    private final int OFFSETVALUE = 87;

    @Id
    @GenericGenerator(name="su_uuid", strategy = "uuid")
    @GeneratedValue(generator = "su_uuid")
    private String id;
    private String userName;
    private String password;
    private String state;
    @OneToOne(targetEntity = Person.class)
    @JoinColumn(name = "personId", referencedColumnName = "id", unique = true)
    private Person person;//所关联的部门成员
    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "su_user_role", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"),
                    inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<Role>();

    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode() * OFFSETVALUE;
    }
}
