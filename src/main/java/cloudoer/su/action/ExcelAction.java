package cloudoer.su.action;

import cloudoer.su.base.impl.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

@Controller
@Scope("prototype")
public class ExcelAction extends BaseAction {
    private String downPath;
    private String fileName;

    public String getDownPath() {
        return downPath;
    }

    public void setDownPath(String downPath) {
        this.downPath = downPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getInputStream () {
       return ServletActionContext.getServletContext().getResourceAsStream(downPath);

    }

    public String getExcel() throws UnsupportedEncodingException {
        fileName = downPath;
        this.downPath = "WEB-INF/excel/"+downPath;
        //ServletActionContext.getResponse().setContentType("application/x-execl");
        //ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;filename=" + new String("班级数据.xls".getBytes(), "ISO-8859-1"));
        //ServletActionContext.getResponse().setHeader("Content-Disposition","attachment;fileName="+java.net.URLEncoder.encode(fileName, "UTF-8"));
        return "success";
    }
}
