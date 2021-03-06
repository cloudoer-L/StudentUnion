package cloudoer.su.action;

import cloudoer.su.base.impl.BaseAction;
import cloudoer.su.entity.Department;
import cloudoer.su.entity.Member;
import org.apache.struts2.ServletActionContext;
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
public class DepartmentAction extends BaseAction {
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
        List<Department> list = departmentService.getAll();
        ajaxJson(list,new String[]{"lower","department"});
        return null;
    }

    public String getByPage() throws IOException {
        int pageNo = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
        int pageSize = Integer.parseInt(ServletActionContext.getRequest().getParameter("rows"));
        List<Department> list = departmentService.getByPage(pageNo, pageSize);
        ajaxJson(list, new String[]{"lower","department"});
        return null;
    }

    public String getById () throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        Department department = departmentService.getById(id);
        ajaxJson(department, new String[]{"lower","department"});
        return null;
    }

    public String getByNumber () throws IOException {
        String number = ServletActionContext.getRequest().getParameter("number");
        Department department = departmentService.getById(number);
        ajaxJson(department, new String[]{"lower","department"});
        return null;
    }

    public String add() throws IOException {
        String superiorId = ServletActionContext.getRequest().getParameter("superiorId");
        departmentService.add(department, superiorId);
        ajaxSuccess("添加成功");
        return null;
    }

    public String update() throws IOException {
        String superiorId = ServletActionContext.getRequest().getParameter("superiorId");
        String leaderId = ServletActionContext.getRequest().getParameter("leaderId");
        departmentService.update(department,leaderId, superiorId);
        ajaxSuccess("修改成功");
        return null;
    }

    public String delete() throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        departmentService.delete(id);
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
            String msg = departmentService.importFile(file);
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
        ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;filename=" + new String("部门数据.xls".getBytes(), "ISO-8859-1"));
        departmentService.exportFile(os);
        return null;
    }

    public String getMembers () throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        Set<Member> set = departmentService.getMembers(id);
        ajaxJson(set, new String[]{"department"});
        return null;
    }

}
