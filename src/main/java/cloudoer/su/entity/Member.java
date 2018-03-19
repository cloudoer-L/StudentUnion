package cloudoer.su.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 部门成员
 * Created by cloudoer on 2018/2/2.
 */
@Entity
@Table(name = "su_member")
public class Member implements Serializable {
    @Transient
    private final int OFFSETVALUE = 83;

    @Id
    @GenericGenerator(name="su_uuid", strategy = "uuid")
    @GeneratedValue(generator = "su_uuid")
    private String id;
    private String name;
    private String number;
    private String state;
    @OneToOne(targetEntity = Person.class)
    @JoinColumn(name = "personId", referencedColumnName = "id", unique = true)
    private Person person;//对应的人员信息
    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "departmentId", referencedColumnName = "id")
    private Department department;//所属部门
    @OneToOne(targetEntity = Role.class)
    @JoinColumn(name = "roleId", referencedColumnName = "id", unique = true)
    private Role role;//在部门中担任的职务

    public Member(){}

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", state='" + state + '\'' +
                ", person=" + person +
                ", department=" + department +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        return number.equals(member.number);

    }

    @Override
    public int hashCode() {
        return number.hashCode() * OFFSETVALUE;
    }
}
