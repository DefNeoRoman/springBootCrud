package app.dao;


import app.entity.Role;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleDao extends GenericDao<Long,Role> {

	Role getRoleByRoleName(String roleName);

}