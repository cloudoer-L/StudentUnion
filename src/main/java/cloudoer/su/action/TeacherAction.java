package cloudoer.su.action;

import cloudoer.su.base.impl.BaseAction;
import cloudoer.su.entity.Teacher;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@Scope("prototype")
public class TeacherAction extends BaseAction {
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
        List<Teacher> list = teacherService.getAll();
        ajaxJson(list,new String[]{"classes"});
        return null;
    }

    public String getByPage() throws IOException {
        int pageNo = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
        int pageSize = Integer.parseInt(ServletActionContext.getRequest().getParameter("rows"));
        List<Teacher> list = teacherService.getByPage(pageNo, pageSize);
        ajaxJson(list, new String[]{"classes"});
        return null;
    }

    public String getById () throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        Teacher teacher = teacherService.getById(id);
        ajaxJson(teacher, new String[]{"classes"});
        return null;
    }

    public String getByNumber () throws IOException {
        String number = ServletActionContext.getRequest().getParameter("number");
        Teacher teacher = teacherService.getById(number);
        ajaxJson(teacher, new String[]{"classes"});
        return null;
    }

    public String add() throws IOException {
        teacherService.add(teacher);
        ajaxSuccess("添加成功");
        return null;
    }

    public String update() throws IOException {
        teacherService.update(teacher);
        ajaxSuccess("修改成功");
        return null;
    }

    public String delete() throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        teacherService.delete(id);
        ajaxSuccess("删除成功");
        return null;
    }

    public String importFile() throws IOException {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) ServletActionContext.getRequest();
        // 获得上传的文件名
        String fileName = wrapper.getFileNames("importExcel")[0];
        // 获得文件过滤器
        File file = wrapper.getFiles("importExcel")[0];
        if (file == null){
            ajaxError("文件为空，导入失败");
        }
        if (fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){//检查是否是Excel文件
            String msg = teacherService.importFile(file);
            ajaxSuccess(msg);
        }else {
            ajaxError("文件格式错误，请按规范提交");
        }
        return null;
    }
}
