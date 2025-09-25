package thymeleaf.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import thymeleaf.constant.Constant;
import thymeleaf.entity.User;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        User u = (User) session.getAttribute(Constant.ATTR_USER);
        model.addAttribute("user", u);
        return "home";
    }

    @PostMapping("/home")
    public String homePost(HttpSession session, Model model) {
        User u = (User) session.getAttribute(Constant.ATTR_USER);
        if (u == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", u);
        return "home";
    }
}