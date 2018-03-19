package cloudoer.su.action;

import cloudoer.su.base.impl.BaseAction;
import cloudoer.su.entity.User;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String indexUI(){
        return "success";
    }

    public String westUI(){
        return "success";
    }

    public String loginUI(){
        return "success";
    }

    public String addUI(){
        return "success";
    }

    public String updateUI(){
        return "success";
    }

    public String login(){
        System.out.println(user);
        User temp = userService.login(user);
        if (temp == null){
            return "error";
        }else {
            ServletActionContext.getRequest().getSession().setAttribute("user", temp);
            return "success";
        }
    }

    public String add(){
        return null;
    }

    public String update(){
        return null;
    }

    public String delete(){
        return null;
    }
}
