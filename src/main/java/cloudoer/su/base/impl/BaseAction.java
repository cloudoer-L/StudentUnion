package cloudoer.su.base.impl;

import cloudoer.su.service.*;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseAction extends ActionSupport {

    @Resource(name = "menuService")
    protected MenuService menuService;

    @Resource(name = "userService")
    protected UserService userService;

    @Resource(name = "teacherService")
    protected TeacherService teacherService;

    @Resource(name = "classesService")
    protected ClassesService classesService;

    @Resource(name = "positionService")
    protected PositionService positionService;

    @Resource(name = "dormitoryService")
    protected DormitoryService dormitoryService;

    @Resource(name = "studentService")
    protected StudentService studentService;

    /**
     * ajax向前端传送json数据
     * @param object 需要传送的对象
     * @param excludes 需要忽略的属性
     * @throws IOException
     */
    protected void ajaxJson(Object object, String[] excludes) throws IOException {
        //解决hibernate关系死循环
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setIgnoreDefaultExcludes(false); //设置默认忽略
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);//设置循环策略为忽略    解决json最头疼的问题 死循环
        jsonConfig.setExcludes(excludes);//此处是亮点，只要将所需忽略字段加到数组中即可

        JSONArray jsonArray = JSONArray.fromObject(object,jsonConfig);
        ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
        /*String jsonStr = jsonArray.toString();
        if ("[".equals(jsonStr.substring(0,1))){
            jsonStr = jsonStr.substring(1,jsonStr.length()-1);
        }
        System.out.println(jsonArray);*/
        ServletActionContext.getResponse().getWriter().print(jsonArray);
    }

    /**
     * ajax向前端传送json数据
     * @param object 需要传送的对象
     * @throws IOException
     */
    public void ajaxJson(Object object) throws IOException{
        JSONArray jsonArray = JSONArray.fromObject(object);
        ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
        String jsonStr = jsonArray.toString();
        if ("[".equals(jsonStr.substring(0, 1))){
            jsonStr = jsonStr.substring(1, jsonStr.length()-1);//去掉json字符串的前后[]符号
        }
        ServletActionContext.getResponse().getWriter().print(jsonStr);
    }

    /**
     * 向前端传送成功的消息
     * @throws IOException
     */
    public void ajaxSuccess (String msg) throws IOException {
        ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
        Map<String, String> map = new HashMap<String, String>();
        map.put("status","success");
        map.put("description",msg);
        JSONArray jsonArray = JSONArray.fromObject(map);
        ServletActionContext.getResponse().getWriter().print(jsonArray);
    }

    /**
     * 向前端传送失败的消息
     * @throws IOException
     */
    public void ajaxError (String msg) throws IOException {
        ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
        Map<String, String> map = new HashMap<String, String>();
        map.put("status","error");
        map.put("description",msg);
        JSONArray jsonArray = JSONArray.fromObject(map);
        ServletActionContext.getResponse().getWriter().print(jsonArray);
    }

    public Map<String,Object> getExcalFile() throws IOException {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) ServletActionContext.getRequest();
        String fileName = wrapper.getFileNames("importExcel")[0];
        File file = wrapper.getFiles("importExcel")[0];
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("file", file);
        map.put("name",fileName);
        return map;
    }
}
