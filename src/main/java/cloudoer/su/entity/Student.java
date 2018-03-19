package cloudoer.su.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cloudoer on 2018/1/31.
 */
@Entity
@Table(name = "su_student")
public class Student extends Person {

    private String state;

    @ManyToOne(targetEntity = Classes.class)
    @JoinColumn(name = "classesId", referencedColumnName = "id")
    private Classes classes;//所在班级
    @ManyToOne(targetEntity = Dormitory.class)
    @JoinColumn(name = "dormitoryId",referencedColumnName = "id")
    private Dormitory dormitory;//所在寝室
    @OneToMany(targetEntity = Position.class)
    @JoinColumn(name = "positionId", referencedColumnName = "id")
    @OrderBy("number")
    private Set<Position> positions = new HashSet<Position>();//担任的班级职务

    public Student(){}

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", sex='" + sex + '\'' +
                ", idCard='" + idCard + '\'' +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                "state='" + state + '\'' +
                ", classes=" + classes +
                ", dormitory=" + dormitory +
                '}';
    }
}
