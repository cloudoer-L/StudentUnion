package cloudoer.su.action;

import cloudoer.su.base.impl.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class IndexAction extends BaseAction {
    public String indexUI(){
        return "success";
    }

    public String westUI(){
        return "success";
    }

    public String loginUI(){
        return "success";
    }
}
