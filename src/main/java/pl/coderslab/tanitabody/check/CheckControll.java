package pl.coderslab.tanitabody.check;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sprawdzam")
public class CheckControll {

    @RequestMapping("/abc")
    @ResponseBody
    public String test(){
        return "Sprawdzam";
    }
}
