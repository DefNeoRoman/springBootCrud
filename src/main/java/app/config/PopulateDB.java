package app.config;


import app.entity.Role;
import app.entity.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class PopulateDB {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public void init() {

        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        User admin = new User();
        admin.setName("admin");
        admin.setPassword("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);
        admin.setRoles(adminRoles);
        userService.addUser(admin);


        User user = new User();
        user.setName("user");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(new Role("ROLE_USER"));
        user.setRoles(userRoles);
        userService.addUser(user);



        User userUp = new User();
        userUp.setName("userUp");
        Set<Role> userUpRoles = new HashSet<>();
        userUpRoles.add(roleUser);
        userUp.setRoles(userUpRoles);
        userService.addUser(userUp);




     }
}
