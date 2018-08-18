package app.config;


import app.model.Role;
import app.model.User;
import app.service.interfaces.RoleService;
import app.service.interfaces.UserService;
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

       initAdmin();
       initUser();
       initUserUp();
       changeName();
     }


    private void initAdmin() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        User admin = new User();
        admin.setName("admin");
        admin.setPassword("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);
        System.out.println("adminRoles size: " + adminRoles.size());
        admin.setRoles(adminRoles);
        userService.addUser(admin);

    }

    private void initUser() {
        Role roleById = roleService.getRoleById(2L);
        User user = new User();
        user.setName("user");
        user.setPassword("user");
        roleById.getUsers().add(user);
        roleService.updateRoles(roleById);
     }

    private void initUserUp() {
        Role roleUser = roleService.getRoleById(2L);
        User userUp = new User();
        userUp.setName("userUp");
        userUp.setPassword("user");
        roleUser.getUsers().add(userUp);
        roleService.updateRoles(roleUser);
    }
    private void changeName() {
        User userUp = userService.getUserById(2L);
        userUp.setName("userUpgggggggggggg");
        userUp.setPassword("user");
        userService.updateUser(userUp);

    }
}
