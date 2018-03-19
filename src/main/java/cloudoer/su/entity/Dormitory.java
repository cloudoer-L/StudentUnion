package cloudoer.su.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 寝室
 * Created by cloudoer on 2018/2/1.
 */
@Entity
@Table(name = "su_dormitory")
public class Dormitory implements Serializable {
    @Transient
    private final int OFFSETVALUE = 82;

    @Id
    @GenericGenerator(name="su_uuid", strategy = "uuid")
    @GeneratedValue(generator = "su_uuid")
    private String id;
    private String name;//楼名
    private String number;//门牌号
    private String state;
    @OneToOne(targetEntity = Student.class)
    @JoinColumn(name = "adminId", referencedColumnName = "id", unique = true)
    private Student admin;//室长
    @OneToMany(targetEntity = Student.class, mappedBy = "dormitory")
    @OrderBy("number")
    private Set<Student> students = new HashSet<Student>();
    @ManyToMany(targetEntity = Classes.class)
    @JoinTable(name = "su_classes_dormitory",
            joinColumns = @JoinColumn(name = "dormitoryId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "classesId", referencedColumnName = "id"))
    @OrderBy("number")
    private Set<Classes> classes = new HashSet<Classes>();

    public Dormitory(){}

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

    public Student getAdmin() {
        return admin;
    }

    public void setAdmin(Student admin) {
        this.admin = admin;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Classes> getClasses() {
        return classes;
    }

    public void setClasses(Set<Classes> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Dormitory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", state='" + state + '\'' +
                ", admin=" + admin.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dormitory dormitory = (Dormitory) o;

        return number.equals(dormitory.number);

    }

    @Override
    public int hashCode() {
        return number.hashCode() * OFFSETVALUE;
    }
}
