package cloudoer.su.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * 菜单/权限
 */
@Entity
@Table(name = "su_menu")
public class Menu implements Serializable {
    @Transient
    private final int OFFSETVALUE = 88;
    @Transient
    private String _parentId;//方便easeUI关联上下级关系

    @Id
    @GenericGenerator(name="su_uuid", strategy = "uuid")
    @GeneratedValue(generator = "su_uuid")
    private String id;
    private String name;//菜单/权限名（中文）
    /*
     * 编号规则：1、共三级菜单，每一级占三位数，共九位数
     *           2、每一级的前两位是主要序号，第三位是补充序号
     *           3、0代表没有
     *           4、示例：一级菜单：010000000，二级菜单：010010000，三级菜单：010010010
     */
    private String number;//序号，将根据这个字段来排序
    private String value;//权限值（根据这个字段进行权限控制）
    private String ico;//菜单图标
    private String url;//对应的请求路径
    private String introduce;//说明
    private String grade;//菜单级别
    private String state;//状态（-1：仅作为权限，1：仅作为菜单，0：既是权限也是菜单）
    @ManyToOne(targetEntity = Menu.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "superiorId", referencedColumnName = "id")
    private Menu superior;//上级菜单
    @OneToMany(targetEntity = Menu.class, mappedBy = "superior", fetch = FetchType.EAGER)
    @OrderBy("number")
    private Set<Menu> lower;//下级菜单

    public Menu(){}

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
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

    public Menu getSuperior() {
        return superior;
    }

    public void setSuperior(Menu superior) {
        this.superior = superior;
    }

    public Set<Menu> getLower() {
        return lower;
    }

    public void setLower(Set<Menu> lower) {
        this.lower = lower;
    }

    public String get_parentId() {
        return _parentId;
    }

    public void set_parentId(String _parentId) {
        this._parentId = _parentId;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                "_parentId='" + _parentId + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", value='" + value + '\'' +
                ", ico='" + ico + '\'' +
                ", url='" + url + '\'' +
                ", introduce='" + introduce + '\'' +
                ", grade='" + grade + '\'' +
                ", state='" + state + '\'' +
                //", superior=" + superior.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        Menu menu = (Menu) o;
        return id.equals(menu.getId());
    }

    @Override
    public int hashCode() {

        return id.hashCode() * OFFSETVALUE;
    }
}
