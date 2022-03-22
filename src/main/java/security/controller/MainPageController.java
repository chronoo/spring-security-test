package security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @GetMapping("/main")
    public String hello(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        return "main.html";
    }
}
