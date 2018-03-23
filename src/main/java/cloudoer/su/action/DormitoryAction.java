package cloudoer.su.action;

import cloudoer.su.base.impl.BaseAction;
import cloudoer.su.entity.Dormitory;
import cloudoer.su.entity.Student;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class DormitoryAction extends BaseAction {
    private Dormitory dormitory;

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
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
        List<Dormitory> list = dormitoryService.getAll();
        ajaxJson(list,new String[]{"students","dormitory"});
        return null;
    }

    public String getByPage() throws IOException {
        int pageNo = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
        int pageSize = Integer.parseInt(ServletActionContext.getRequest().getParameter("rows"));
        List<Dormitory> list = dormitoryService.getByPage(pageNo, pageSize);
        ajaxJson(list, new String[]{"students","dormitory"});
        return null;
    }

    public String getById () throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        Dormitory dormitory = dormitoryService.getById(id);
        ajaxJson(dormitory, new String[]{"students","dormitory"});
        return null;
    }

    public String getByNumber () throws IOException {
        String number = ServletActionContext.getRequest().getParameter("number");
        Dormitory dormitory = dormitoryService.getById(number);
        ajaxJson(dormitory, new String[]{"students","dormitory"});
        return null;
    }

    public String getStudent() throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        ajaxJson(dormitoryService.getStudent(id), new String[]{"dormitory","classes"});
        return null;
    }

    public String add() throws IOException {
        dormitoryService.add(dormitory);
        ajaxSuccess("添加成功");
        return null;
    }

    public String update() throws IOException {
        String adminId = ServletActionContext.getRequest().getParameter("adminId");
        dormitoryService.update(dormitory, adminId);
        ajaxSuccess("修改成功");
        return null;
    }

    public String delete() throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        dormitoryService.delete(id);
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
            String msg = dormitoryService.importFile(file);
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
        ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;filename=" + new String("寝室数据.xls".getBytes(), "ISO-8859-1"));
        dormitoryService.exportFile(os);
        return null;
    }
}
