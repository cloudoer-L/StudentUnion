package cloudoer.su.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cloudoer on 2018/2/2.
 */
@Entity
@Table(name = "su_department")
public class Department implements Serializable {
    @Transient
    private final int OFFSETVALUE = 81;

    @Id
    @GenericGenerator(name="su_uuid", strategy = "uuid")
    @GeneratedValue(generator = "su_uuid")
    private String id;
    private String name;
    private String number;
    private String introduce;//部门介绍
    private String state;
    @OneToOne(targetEntity = Member.class)
    @JoinColumn(name = "leaderId", referencedColumnName = "id", unique = true)
    private Member leader;//部门负责人
    @OneToMany(targetEntity = Member.class, mappedBy = "department")
    @OrderBy("number")
    private Set<Member> members = new HashSet<Member>();//部门成员
    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "superiorId", referencedColumnName = "id")
    private Department superior;//上级部门
    @OneToMany(targetEntity = Department.class, mappedBy = "superior")
    @OrderBy("number")
    private Set<Department> lower = new HashSet<Department>();//下级部门

    public Department(){};

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

    public Member getLeader() {
        return leader;
    }

    public void setLeader(Member leader) {
        this.leader = leader;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public Department getSuperior() {
        return superior;
    }

    public void setSuperior(Department superior) {
        this.superior = superior;
    }

    public Set<Department> getLower() {
        return lower;
    }

    public void setLower(Set<Department> lower) {
        this.lower = lower;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", introduce='" + introduce + '\'' +
                ", state='" + state + '\'' +
                ", leader=" + leader.getName() +
                ", superior=" + superior +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        return number.equals(that.number);

    }

    @Override
    public int hashCode() {
        return number.hashCode() * OFFSETVALUE;
    }
}
