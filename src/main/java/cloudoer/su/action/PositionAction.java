package cloudoer.su.action;

import cloudoer.su.base.impl.BaseAction;
import cloudoer.su.entity.Position;
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
public class PositionAction extends BaseAction{
    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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
        List<Position> list = positionService.getAll();
        ajaxJson(list, new String[]{"classCommittees"});
        return null;
    }

    public String getByPage() throws IOException {
        int pageNo = Integer.parseInt(ServletActionContext.getRequest().getParameter("page"));
        int pageSize = Integer.parseInt(ServletActionContext.getRequest().getParameter("rows"));
        List<Position> list = positionService.getByPage(pageNo, pageSize);
        ajaxJson(list, new String[]{"classCommittees"});
        return null;
    }

    public String getById () throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        Position position = positionService.getById(id);
        ajaxJson(position);
        return null;
    }

    public String getByNumber() throws IOException {
        String number = ServletActionContext.getRequest().getParameter("number");
        Position position = positionService.getByNumber(number);
        ajaxJson(position);
        return null;
    }

    public String add() throws IOException {
        positionService.add(position);
        ajaxSuccess("添加成功");
        return null;
    }

    public String update() throws IOException {
        positionService.update(position);
        ajaxSuccess("修改成功");
        return null;
    }

    public String delete () throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        positionService.delete(id);
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
            String msg = positionService.importFile(file);
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
        ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;filename=" + new String("班级职务数据.xls".getBytes(), "ISO-8859-1"));
        positionService.exportFile(os);
        return null;
    }
}
