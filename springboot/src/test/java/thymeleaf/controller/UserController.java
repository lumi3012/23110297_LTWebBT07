package thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import thymeleaf.entity.User;
import thymeleaf.service.UserService;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    public String listUsers(Model model) {
        List<User> list = userService.findAll();
        model.addAttribute("users", list);
        return "users/list";
    }

    @GetMapping("/admin/users/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        User u = userService.findById(id);
        model.addAttribute("user", u);
        return "users/edit";
    }

    @GetMapping("/admin/users/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users/update")
    public String updateUser(@RequestParam Long id,
                             @RequestParam String fullname,
                             @RequestParam String phone,
                             @RequestParam String email,
                             @RequestParam Integer roleid) {
        User u = userService.findById(id);
        u.setFullname(fullname);
        u.setPhone(phone);
        u.setEmail(email);
        u.setRoleid(roleid);
        userService.update(u);
        return "redirect:/admin/users";
    }
}