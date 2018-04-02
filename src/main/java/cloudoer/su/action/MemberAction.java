package cloudoer.su.action;

import cloudoer.su.base.impl.BaseAction;
import cloudoer.su.entity.Member;
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
public class MemberAction extends BaseAction {
    private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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
        List<Member> list = memberService.getAll();
        ajaxJson(list,new String[]{"leader","members","superior","lower","classes","dormitory"});
        return null;
    }

    public String getByPage() throws IOException {
        int pageNo = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
        int pageSize = Integer.parseInt(ServletActionContext.getRequest().getParameter("rows"));
        List<Member> list = memberService.getByPage(pageNo, pageSize);
        ajaxJson(list, new String[]{"leader","members","superior","lower","classes","dormitory"});
        return null;
    }

    public String getById () throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        Member member = memberService.getById(id);
        ajaxJson(member, new String[]{"leader","members","superior","lower","classes","dormitory"});
        return null;
    }

    public String getByNumber () throws IOException {
        String number = ServletActionContext.getRequest().getParameter("number");
        Member member = memberService.getById(number);
        ajaxJson(member, new String[]{"leader","members","superior","lower","classes","dormitory"});
        return null;
    }

    public String add() throws IOException {
        String personNumber = ServletActionContext.getRequest().getParameter("personNumber");
        String departmentNumber = ServletActionContext.getRequest().getParameter("departmentNumber");
        memberService.add(member, personNumber, departmentNumber);
        ajaxSuccess("添加成功");
        return null;
    }

    public String update() throws IOException {
        String personNumber = ServletActionContext.getRequest().getParameter("personNumber");
        String departmentNumber = ServletActionContext.getRequest().getParameter("departmentNumber");
        memberService.update(member, personNumber, departmentNumber);
        ajaxSuccess("修改成功");
        return null;
    }

    public String delete() throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        memberService.delete(id);
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
            String msg = memberService.importFile(file);
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
        ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;filename=" + new String("部员数据.xls".getBytes(), "ISO-8859-1"));
        memberService.exportFile(os);
        return null;
    }
}
