package cloudoer.su.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 班委，用来连接班级、学生和职务的
 * 全部使用双向一对多的连接方式
 */
@Entity
@Table(name = "su_classCommittee")
public class ClassCommittee implements Serializable {
    @Transient
    private final int OFFSETVALUE = 88;

    @Id
    @GenericGenerator(name="su_uuid", strategy = "uuid")
    @GeneratedValue(generator = "su_uuid")
    private String id;
    private String studentName;
    private String positionName;
    private String positionNumber;

    @ManyToOne(targetEntity = Classes.class)
    @JoinColumn(name = "classesId", referencedColumnName = "id")
    private Classes classesC;
    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name = "studentId",referencedColumnName = "id")
    private Student student;
    @ManyToOne(targetEntity = Position.class)
    @JoinColumn(name = "positionId", referencedColumnName = "id")
    private Position position;

    public ClassCommittee(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(String positionNumber) {
        this.positionNumber = positionNumber;
    }

    public Classes getClassesC() {
        return classesC;
    }

    public void setClassesC(Classes classes) {
        this.classesC = classes;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "ClassCommittee{" +
                "id='" + id + '\'' +
                ", studentName='" + studentName + '\'' +
                ", positionName='" + positionName + '\'' +
                ", positionNumber='" + positionNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classes classes = (Classes) o;

        return id.equals(classes.getId());

    }

    @Override
    public int hashCode() {
        return id.hashCode() * OFFSETVALUE;
    }
}
