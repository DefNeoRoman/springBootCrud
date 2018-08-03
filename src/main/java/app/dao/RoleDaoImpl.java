package app.dao;


import app.entity.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class RoleDaoImpl extends AbstractDao<Long, Role> implements RoleDao {

	public Role getRoleByRoleName(String roleName) {
		return (Role) getSession().createQuery("FROM Role WHERE name = :name").setParameter("name", roleName).uniqueResult();
	}

}