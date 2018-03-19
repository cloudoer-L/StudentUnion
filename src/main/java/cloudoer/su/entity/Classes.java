package cloudoer.su.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 班级
 * Created by cloudoer on 2018/1/31.
 */
@Entity
@Table(name = "su_classes")
public class Classes implements Serializable {
    @Transient
    private final int OFFSETVALUE = 80;

    @Id
    @GenericGenerator(name="su_uuid", strategy = "uuid")
    @GeneratedValue(generator = "su_uuid")
    private String id;
    private String name;
    private String number;
    private String grade;//年级
    private String state;
    @ManyToOne(targetEntity = Teacher.class)
    @JoinColumn(name = "teacherId", referencedColumnName = "id", nullable = true)
    private Teacher teacher;//班主任
    @OneToMany(targetEntity = Student.class, mappedBy = "classes")
    @OrderBy("number")
    private Set<Student> students = new HashSet<Student>();
    @ManyToMany(targetEntity = Dormitory.class)
    @JoinTable(name = "su_classes_dormitory",
            joinColumns = @JoinColumn(name = "classesId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "dormitoryId", referencedColumnName = "id"))
    @OrderBy("number")
    private Set<Dormitory> dormitories = new HashSet<Dormitory>();

    public Classes(){}

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Dormitory> getDormitories() {
        return dormitories;
    }

    public void setDormitories(Set<Dormitory> dormitories) {
        this.dormitories = dormitories;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", grade='" + grade + '\'' +
                ", state='" + state + '\'' +
                ", teacher=" + teacher +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classes classes = (Classes) o;

        return number.equals(classes.number);

    }

    @Override
    public int hashCode() {
        return number.hashCode() * OFFSETVALUE;
    }
}
