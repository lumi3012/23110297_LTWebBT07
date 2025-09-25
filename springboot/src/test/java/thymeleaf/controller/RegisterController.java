package thymeleaf.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import thymeleaf.entity.User;
import thymeleaf.service.UserService;

import java.io.File;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String fullname,
                           @RequestParam String password,
                           @RequestParam String phone,
                           @RequestParam String email,
                           @RequestParam("avatar") MultipartFile avatar,
                           Model model) {
        try {
            String avatarPath = null;
            if (!avatar.isEmpty()) {
                String uploadDir = "uploads/avatars/";
                new File(uploadDir).mkdirs();
                String fileName = avatar.getOriginalFilename();
                avatar.transferTo(new File(uploadDir + fileName));
                avatarPath = uploadDir + fileName;
            }

            User u = new User();
            u.setUsername(username);
            u.setFullname(fullname);
            u.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));
            u.setPhone(phone);
            u.setEmail(email);
            u.setAvatar(avatarPath);
            u.setRoleid(3);
            u.setActive(true);

            userService.register(u);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Đăng ký thất bại: " + e.getMessage());
            return "register";
        }
    }
}