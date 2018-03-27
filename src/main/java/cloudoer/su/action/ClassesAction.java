package cloudoer.su.action;

import cloudoer.su.base.impl.BaseAction;
import cloudoer.su.entity.ClassCommittee;
import cloudoer.su.entity.Classes;
import cloudoer.su.entity.Student;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public String importUI(){
        return "success";
    }

    public String infoUI(){
        return "success";
    }

    public String messageUI(){
        return "success";
    }

    public String classes_studentsUI(){
        return "success";
    }

    public String classes_committeeUI(){
        return "success";
    }

    public String getAll() throws IOException {
        List<Classes> list = classesService.getAll();
        ajaxJson(list, new String[]{"classes","students","classCommittees","teacher"});
        return null;
    }

    public String getByPage() throws IOException {
        int pageNo = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
        int pageSize = Integer.parseInt(ServletActionContext.getRequest().getParameter("rows"));
        List<Classes> list = classesService.getByPage(pageNo, pageSize);
        ajaxJson(list, new String[]{"classes","students","classCommittees"});
        return null;
    }

    public String getById () throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        Classes classes = classesService.getById(id);
        ajaxJson(classes, new String[]{"classes","students","classCommittees"});
        return null;
    }

    public String getByNumber() throws IOException {
        String number = ServletActionContext.getRequest().getParameter("number");
        Classes classes = classesService.getByNumber(number);
        ajaxJson(classes, new String[]{"classes","students","classCommittees"});
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

    public String importFile() throws IOException {
        Map<String, Object> map = getExcalFile();
        File file = (File) map.get("file");
        String fileName = (String) map.get("name");
        if (file == null){
            ajaxError("文件为空，导入失败");
            return null;
        }
        if (fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){//检查是否是Excel文件
            String msg = classesService.importFile(file);
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
        ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;filename=" + new String("班级数据.xls".getBytes(), "ISO-8859-1"));
        classesService.exportFile(os);
        return null;
    }

    public String getStudents() throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        Set<Student> set = classesService.getStudents(id);
        ajaxJson(set, new String[]{"classes","students","classCommittees"});
        return null;
    }

    public String getClassCommittee() throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        Set<ClassCommittee> set = classesService.getClassCommittee(id);
        ajaxJson(set, new String[]{"classesC","classCommittees","position"});
        return null;
    }
}
