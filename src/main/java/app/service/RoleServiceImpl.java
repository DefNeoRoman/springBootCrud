package app.service;


import app.dao.RoleDao;
import app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	private final RoleDao roleDao;

	@Autowired
	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void addRole(Role role) {
		 roleDao.persist(role);
	}

	public Role getRoleByRoleName(String roleName) {
		return roleDao.getRoleByRoleName(roleName);
	}

	public Role getRoleById(Long id) {
		return roleDao.getByKey(id);
	}

	public List<Role> getAllRoles() {
		return roleDao.getAll();
	}

	public void updateRoles(Role role) {
		 roleDao.update(role);
	}

	public void deleteRoleById(Long id) {
		 roleDao.deleteByKey(id);
	}
}
