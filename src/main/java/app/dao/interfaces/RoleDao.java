package app.dao.interfaces;


import app.dao.interfaces.GenericDao;
import app.model.Role;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleDao extends GenericDao<Long,Role> {

	Role getRoleByRoleName(String roleName);

}