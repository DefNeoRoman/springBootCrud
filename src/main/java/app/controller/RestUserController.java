package app.controller;

import app.model.Role;
import app.model.User;
import app.service.interfaces.RoleService;
import app.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: rest -> api
@RequestMapping("/rest/user")
@RestController
public class RestUserController {

    private final
    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final
    RoleService roleService;

    private final
    UserService userService;

    @Autowired
    public RestUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public  List<User> welcome() {

        return userService.getAllUser();
    }

    @GetMapping("/edit")
    public  User edit(@RequestParam Long id) {


        return userService.getUserById(id);
    }
    // Put - обновление
    @PutMapping(value = "/edit")
    public void postEdit(  @RequestBody User user,
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

    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/add")
    public User add() {

        return new User();
    }
    //Post - создание
    @PostMapping
    public void postAdd(@RequestBody User user) {
        userService.addUser(user);
    }
}
