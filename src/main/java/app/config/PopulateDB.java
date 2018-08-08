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
    RoleService roleService;


    public void init() {

       initAdmin();
       initUser();
       initUserUp();
       changeName();
     }


    public void initAdmin() {
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

    public void initUser() {
        Role roleById = roleService.getRoleById(2L);
        User user = new User();
        user.setName("user");
        user.setPassword("user");
        roleById.getUsers().add(user);
        roleService.updateRoles(roleById);
     }

    public void initUserUp() {
        Role roleUser = roleService.getRoleById(2L);
        User userUp = new User();
        userUp.setName("userUp");
        userUp.setPassword("user");
        roleUser.getUsers().add(userUp);
        roleService.updateRoles(roleUser);
    }
    public void changeName() {
        User userUp = userService.getUserById(2L);
        userUp.setName("userUpgggggggggggg");
        userUp.setPassword("user");
        userService.updateUser(userUp);

    }
}
