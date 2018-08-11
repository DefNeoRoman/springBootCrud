package app.controller;

import app.entity.Role;
import app.entity.User;
import app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WelcomeController {

    final RoleService roleService;

    @Autowired
    public WelcomeController(RoleService roleService) {
          this.roleService = roleService;
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
        Role roleUser = roleService.getRoleByRoleName("ROLE_USER");
        roleUser.getUsers().add(user);
        roleService.updateRoles(roleUser);
        return "redirect:/";
    }
}
