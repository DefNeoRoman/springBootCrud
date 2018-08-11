package app.controller;

import app.entity.Role;
import app.entity.User;
import app.service.RoleService;
import app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RequestMapping("/user")
@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    final
    RoleService roleService;

    final
    UserService userService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String welcome(Map<String, Object> model) {
        model.put("users", userService.getAllUser());
        return "welcome";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Map<String, Object> model) {

        User userById = userService.getUserById(id);
        Set<Role> roles = userById.getRoles();
        model.put("roleUser",false);
        model.put("roleAdmin",false);
        roles.forEach(role -> {
            if(role.getName().equals("ROLE_USER")){
                model.put("roleUser",true);
            }else if(role.getName().equals("ROLE_ADMIN")){
                model.put("roleAdmin",true);
            }
        });
        model.put("user", userById);

        return "edit";
    }

    @PostMapping(value = "/edit")
    public String postEdit(@ModelAttribute User user,
                           @RequestParam(required = false) String role_user,
                           @RequestParam(required = false) String role_admin) {
        User userById = userService.getUserById(user.getId());
        userById.setName(user.getName());
        userById.setAge(user.getAge());
        userById.setEmail(user.getEmail());
        Role role_user1 = roleService.getRoleByRoleName("ROLE_USER");
        Role role_admin1 = roleService.getRoleByRoleName("ROLE_ADMIN");
        List<User> users = role_user1.getUsers();
        List<User> adminUsers = role_admin1.getUsers();
        if (role_user != null) {
            if(!users.contains(userById)){
                users.add(userById);
            }

        }else{
            users.remove(userById);
        }
        if (role_admin != null) {
            if(!adminUsers.contains(userById)){
                adminUsers.add(userById);
            }
        }else{
            adminUsers.remove(userById);
        }
        roleService.updateRoles(role_user1);
        roleService.updateRoles(role_admin1);
        userService.updateUser(userById);
        return "redirect:/user";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        userService.deleteUserById(id);
        return "redirect:/user";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping(value = "/add")
    public String postAdd(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/user";
    }
}
