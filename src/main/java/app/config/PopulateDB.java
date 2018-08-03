package app.config;


import app.entity.Role;
import app.entity.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Component
public class PopulateDB {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public void init() {

        Role roleAdmin = new Role();
        roleAdmin.setName("ADMIN");

        Role roleUser = new Role();
        roleUser.setName("USER");
        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);
        User admin = new User();
        admin.setName("admin");
        admin.setPassword("admin");

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);
        admin.setRoles(adminRoles);
        User user = new User();
        user.setName("user");
        user.setPassword("user");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleUser);
        user.setRoles(userRoles);


        userService.addUser(admin);
        userService.addUser(user);

     }
}
