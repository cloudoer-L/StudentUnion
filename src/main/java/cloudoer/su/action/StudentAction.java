package cloudoer.su.action;

import cloudoer.su.base.impl.BaseAction;
import cloudoer.su.entity.Student;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class StudentAction extends BaseAction {
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String indexUI (){
        return "success";
    }

    public String addUI(){
        return "success";
    }

    public String importUI(){
        return "success";
    }

    public String getAll() throws IOException {
        List<Student> list = studentService.getAll();
        ajaxJson(list, new String[]{"classes","students"});
        return null;
    }

    public String getByPage() throws IOException {
        int pageNo = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
        int pageSize = Integer.parseInt(ServletActionContext.getRequest().getParameter("rows"));
        List<Student> list = studentService.getByPage(pageNo, pageSize);
        ajaxJson(list, new String[]{"students"});
        return null;
    }

    public String getById () throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        Student student = studentService.getById(id);
        ajaxJson(student, new String[]{"students"});
        return null;
    }

    public String getByNumber() throws IOException {
        String number = ServletActionContext.getRequest().getParameter("number");
        Student student = studentService.getByNumber(number);
        ajaxJson(student, new String[]{"students"});
        return null;
    }

    public String add() throws Exception {
        String classesId = ServletActionContext.getRequest().getParameter("classesId");
        String dormitoryId = ServletActionContext.getRequest().getParameter("dormitoryId");
        studentService.add(student, classesId, dormitoryId);
        ajaxSuccess("添加成功");
        return null;
    }

    public String update() throws Exception {
        String classesId = ServletActionContext.getRequest().getParameter("classesId");
        String dormitoryId = ServletActionContext.getRequest().getParameter("dormitoryId");
        studentService.update(student, classesId, dormitoryId);
        ajaxSuccess("修改成功");
        return null;
    }

    public String delete () throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        studentService.delete(id);
        ajaxSuccess("删除成功");
        return null;
    }

    public String importFile() throws IOException {
        Map<String, Object> map = getExcalFile();
        File file = (File) map.get("file");
        String fileName = (String) map.get("name");
        if (file == null){
            ajaxError("文件为空，导入失败");
            return null;
        }
        if (fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){//检查是否是Excel文件
            String msg = studentService.importFile(file);
            ajaxSuccess(msg);
        }else {
            ajaxError("文件格式错误，请按规范提交");
            return null;
        }
        return null;
    }

    public String exportFile() throws Exception {
        OutputStream os = ServletActionContext.getResponse().getOutputStream();
        ServletActionContext.getResponse().setContentType("application/x-execl");
        ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;filename=" + new String("学生数据.xls".getBytes(), "ISO-8859-1"));
        studentService.exportFile(os);
        return null;
    }
}
