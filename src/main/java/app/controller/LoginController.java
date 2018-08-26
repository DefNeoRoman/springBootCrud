package app.controller;

import app.model.Role;
import app.model.User;
import app.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class LoginController {

    private final
    RoleService roleService;

    @Autowired
    public LoginController(RoleService roleService) {
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
