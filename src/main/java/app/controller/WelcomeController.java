package app.controller;

import app.entity.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class WelcomeController {

    final UserService userService;

    @Autowired
    public WelcomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String welcome(Map<String, Object> model) {
        model.put("users", userService.getAllUser());
        return "welcome";
    }

    @GetMapping("/user/edit")
    public String edit(@RequestParam Long id, Map<String, Object> model) {
        User userById = userService.getUserById(id);
        model.put("user", userById);
        return "edit";
    }

    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public String postEdit(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/user";
    }

    @GetMapping("/user/delete")
    public String delete(@RequestParam Long id) {
        userService.deleteUserById(id);
        return "redirect:/user";
    }

    @GetMapping("/user/add")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String postAdd(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/user";
    }

    @GetMapping(value = "/userTask")
    public String userTask() {

        return "userTask";
    }

    @GetMapping(value = "/adminTask")
    public String adminTask() {

        return "adminTask";
    }
    @GetMapping(value = "/login")
    public String loginPage(Model model){
        return "login";
    }

    @GetMapping(value = "/accessDenied")
    public String accessDenied(){

        return "accessDenied";
    }
    @GetMapping(value = "/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping(value = "/register")
    public String registerPost(@ModelAttribute User user){
        userService.addUser(user);
        return "redirect:/";
    }
}
