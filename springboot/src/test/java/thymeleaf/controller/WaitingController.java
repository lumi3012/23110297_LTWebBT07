package thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WaitingController {
    @GetMapping("/waiting")
    public String waiting() {
        return "waiting";
    }
}