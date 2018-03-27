package cloudoer.su.action;

import cloudoer.su.base.impl.BaseAction;
import cloudoer.su.entity.ClassCommittee;
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
public class ClassCommitteeAction extends BaseAction{
    private ClassCommittee committee;

    public ClassCommittee getCommittee() {
        return committee;
    }

    public void setCommittee(ClassCommittee committee) {
        this.committee = committee;
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
        List<ClassCommittee> list = classCommitteeService.getAll();
        ajaxJson(list,new String[]{"classesC","position","classCommittees","students"});
        return null;
    }

    public String getByPage() throws IOException {
        int pageNo = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
        int pageSize = Integer.parseInt(ServletActionContext.getRequest().getParameter("rows"));
        List<ClassCommittee> list = classCommitteeService.getByPage(pageNo, pageSize);
        ajaxJson(list, new String[]{"classesC","position","students"});
        return null;
    }

    public String getById () throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        ClassCommittee committee = classCommitteeService.getById(id);
        ajaxJson(committee, new String[]{"classesC","position","classCommittees","students"});
        return null;
    }

    public String getByNumber () throws IOException {
        String number = ServletActionContext.getRequest().getParameter("number");
        ClassCommittee committee = classCommitteeService.getById(number);
        ajaxJson(committee, new String[]{"classesC","position","classCommittees","students"});
        return null;
    }

    public String add() throws IOException {
        String classesNumber = ServletActionContext.getRequest().getParameter("classesNumber");
        String studentNumber = ServletActionContext.getRequest().getParameter("studentNumber");
        String positionNumber = ServletActionContext.getRequest().getParameter("positionNumber");
        classCommitteeService.add(classesNumber, studentNumber, positionNumber);
        ajaxSuccess("添加成功");
        return null;
    }

    public String update() throws IOException {
        String classesNumber = ServletActionContext.getRequest().getParameter("classesNumber");
        String studentNumber = ServletActionContext.getRequest().getParameter("studentNumber");
        String positionNumber = ServletActionContext.getRequest().getParameter("positionNumber");
        String id = ServletActionContext.getRequest().getParameter("id");
        classCommitteeService.update(classesNumber, studentNumber, positionNumber,id);
        ajaxSuccess("修改成功");
        return null;
    }

    public String delete() throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        classCommitteeService.delete(id);
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
            String msg = classCommitteeService.importFile(file);
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
        ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;filename=" + new String("班委数据.xls".getBytes(), "ISO-8859-1"));
        classCommitteeService.exportFile(os);
        return null;
    }

}
