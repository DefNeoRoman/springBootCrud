package app.service.interfaces;


import app.model.Role;
import app.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
	User getUserByUsername(String username);

	User getUserById(Long id);

	void addUser(User user);

	List<User> getAllUser();

	void deleteUserById(Long id);

	void updateUser(User user);

	Set<Role> getUserRoles(String login);

}
