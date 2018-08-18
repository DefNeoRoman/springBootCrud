package app.dao.impl;


import app.dao.interfaces.RoleDao;
import app.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class RoleDaoImpl implements RoleDao {

	@PersistenceContext
	private EntityManager em;

	public Role getRoleByRoleName(String roleName) {
		Role role = (Role) em.createQuery("SELECT u FROM Role u WHERE u.name =:rolename").setParameter("rolename", roleName).getSingleResult();
		return role;
	}

	@Override
	public void persist(Role entity) {
		em.persist(entity);
	}

	@Override
	public Role getByKey(Long id) {

		return  em.find(Role.class, id);
	}

	@Override
	public List<Role> getAll() {

		return 	em.createQuery("from Role ").getResultList();
	}

	@Override
	public void update(Role role) {
	 em.merge(role);
	}

	@Override
	public void deleteByKey(Long id) {
		Role role = em.find(Role.class, id);
		em.remove(role);
	}
}