package thymeleaf.controller;

import thymeleaf.constant.Constant;
import thymeleaf.entity.User;
import thymeleaf.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.jsp
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam(required = false) String remember,
                        HttpSession session,
                        Model model) {
        User u = userService.login(username, password);
        if (u == null) {
            model.addAttribute("error", Constant.MSG_INVALID_LOGIN);
            return "login";
        }
        session.setAttribute(Constant.ATTR_USER, u);
        return "redirect:/home";
    }
}
