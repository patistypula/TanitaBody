package pl.coderslab.tanitabody.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String landingPage(){
        return "home/landingPage";
    }

    @GetMapping("/login")
    public String login(){
        return "home/login";
    }

    @RequestMapping("/home")
    public String hello() {
        return  "home/home";
    }

    @GetMapping("/logout")
    public String logOut(){
        return "home/logout";
    }

}
