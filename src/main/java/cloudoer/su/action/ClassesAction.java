package cloudoer.su.action;

import cloudoer.su.base.impl.BaseAction;
import cloudoer.su.entity.Classes;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class ClassesAction extends BaseAction {
    private Classes classes;

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public String indexUI (){
        return "success";
    }

    public String addUI(){
        return "success";
    }

    public String getAll() throws IOException {
        List<Classes> list = classesService.getAll();
        ajaxJson(list, new String[]{"classes"});
        return null;
    }

    public String getByPage() throws IOException {
        int pageNo = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
        int pageSize = Integer.parseInt(ServletActionContext.getRequest().getParameter("rows"));
        List<Classes> list = classesService.getByPage(pageNo, pageSize);
        ajaxJson(list, new String[]{"classes"});
        return null;
    }

    public String getById () throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        Classes classes = classesService.getById(id);
        ajaxJson(classes, new String[]{"classes"});
        return null;
    }

    public String getByNumber() throws IOException {
        String number = ServletActionContext.getRequest().getParameter("number");
        Classes classes = classesService.getByNumber(number);
        ajaxJson(classes, new String[]{"classes"});
        return null;
    }

    public String add() throws IOException {
        String teacherId = ServletActionContext.getRequest().getParameter("teacherId");
        classesService.add(classes, teacherId);
        ajaxSuccess("添加成功");
        return null;
    }

    public String update() throws IOException {
        String teacherId = ServletActionContext.getRequest().getParameter("teacherId");
        classesService.update(classes, teacherId);
        ajaxSuccess("修改成功");
        return null;
    }

    public String delete () throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        classesService.delete(id);
        ajaxSuccess("删除成功");
        return null;
    }
}
