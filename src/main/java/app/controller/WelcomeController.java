package app.controller;

import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class WelcomeController {

    final UserService userService;


    @Value("${welcome.message}")
    private String message = "Hello World";

    @Autowired
    public WelcomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("user",userService.getUserById(1L));
        model.put("message", this.message);
        return "welcome";
    }
}
