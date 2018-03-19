package cloudoer.su.action;

import cloudoer.su.base.impl.BaseAction;
import cloudoer.su.entity.Menu;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class MenuAction extends BaseAction {

    private Menu menu;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String indexUI (){
        return "success";
    }

    public String addUI (){
        return "success";
    }


    public String getMenuTree() throws IOException {
        List<Menu> list = menuService.getAllByGrade();
        ajaxJson(list, new String[]{"superior"});
        return null;
    }

    public String getAll() throws IOException {
        List<Menu> list = menuService.getAll();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total",list.size());
        map.put("rows", list);
        ajaxJson(map, new String[]{"superior","lower"});
        return null;
    }

    public String getById() throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        ajaxJson(menuService.getById(id), new String[]{"superior","lower"});
        return null;
    }

    public String add() throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        menuService.add(menu, id);
        ajaxSuccess("添加成功");
        return null;
    }

    public String update() throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        menu.setId(id);
        menuService.update(menu);
        ajaxSuccess("修改成功");
        return null;
    }

    public String delete () throws IOException {
        String id = ServletActionContext.getRequest().getParameter("id");
        menuService.delete(id);
        ajaxSuccess("删除成功");
        return null;
    }


}
