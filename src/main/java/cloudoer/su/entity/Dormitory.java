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
    private String name;//全称
    private String buildingName;//楼名
    private String buildingNumber;//门牌号
    private String number;//编号
    private Integer capacity;//床位个数
    private Integer alreadyCapacity;//已经住进来的人数
    private String state;
    @OneToOne(targetEntity = Student.class)
    @JoinColumn(name = "adminId", referencedColumnName = "id", unique = true)
    private Student admin;//室长
    @OneToMany(targetEntity = Student.class, mappedBy = "dormitory")
    @OrderBy("classes")
    private Set<Student> students = new HashSet<Student>();

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

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getAlreadyCapacity() {
        return alreadyCapacity;
    }

    public void setAlreadyCapacity(Integer alreadyCapacity) {
        this.alreadyCapacity = alreadyCapacity;
    }

    @Override
    public String toString() {
        return "Dormitory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", number='" + buildingName + '\'' +
                ", state='" + buildingNumber + '\'' +
                ", number='" + capacity + '\'' +
                ", state='" + alreadyCapacity + '\'' +
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
