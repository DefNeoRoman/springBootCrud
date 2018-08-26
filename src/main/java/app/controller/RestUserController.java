package app.controller;

import app.dto.UserTO;
import app.model.Role;
import app.model.User;
import app.service.interfaces.RoleService;
import app.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public List<User> welcome() {

        return userService.getAllUser();
    }
    @GetMapping(value = "/googleEnter")
    public Principal googleEnter(Principal principal) {

        return principal;
    }
    @GetMapping("/edit")
    public User edit(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    // Put - обновление
    @PutMapping(value = "/edit")
    public void postEdit(@RequestBody UserTO user) {
        User userById = userService.getUserById(user.getId());
        userById.setName(user.getName());
        userById.setAge(user.getAge());
        userById.setEmail(user.getEmail());
        List<Role> allRoles = roleService.getAllRoles();
        List<Role> presentRoles = new ArrayList<>();
        Set<Long> rolesID = user.getRolesID();
        rolesID.forEach(roleID -> {
            Role roleById = roleService.getRoleById(roleID);
            presentRoles.add(roleById);
            List<User> users = roleById.getUsers();
            if (!users.contains(userById)) {
                users.add(userById);
            }
        });
        List<Role> result = new ArrayList<>();
        allRoles.forEach(roleIn -> {
            if (!presentRoles.contains(roleIn)) {
                result.add(roleIn);
            }
        });
        result.forEach(role -> role.getUsers().remove(userById));
        allRoles.forEach(roleService::updateRoles);
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
