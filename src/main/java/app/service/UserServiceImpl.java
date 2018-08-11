package app.service;


import app.dao.UserDao;
import app.model.Role;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	private final
	UserDao userDAO;


	@Autowired
	public UserServiceImpl(UserDao userDAO) {
		this.userDAO = userDAO;
	}

	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}

	public User getUserById(Long id) {
		return userDAO.getByKey(id);
	}

	@Override
	public void addUser(User user) {
		userDAO.persist(user);
	}

	public List<User> getAllUser() {
		return userDAO.getAll();
	}

	public void deleteUserById(Long id) {

		userDAO.deleteByKey(id);
	}

	public void updateUser(User user) {
		 userDAO.update(user);
	}

	public Set<Role> getUserRoles(String username) {
		return userDAO.getUserByUsername(username).getRoles();
	}


}
