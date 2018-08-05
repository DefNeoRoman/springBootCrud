package app.dao;


import app.entity.Role;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class RoleDaoImpl implements RoleDao {
	@Autowired
	private final SessionFactory sessionFactory;

	public RoleDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Role getRoleByRoleName(String roleName) {
		return (Role) sessionFactory.getCurrentSession().createQuery("FROM Role WHERE name = :name").setParameter("name", roleName).uniqueResult();
	}

	@Override
	public void persist(Role entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public Role getByKey(Long id) {
		return  sessionFactory.getCurrentSession().get(Role.class, id);
	}

	@Override
	public List<Role> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Role ");
		return (List<Role>) query.list();
	}

	@Override
	public void update(Role group) {
		Role role = sessionFactory.getCurrentSession().get(Role.class, group.getId());
		role.setName(group.getName());
		sessionFactory.getCurrentSession().save(role);
	}

	@Override
	public void deleteByKey(Long id) {
		throw new UnsupportedOperationException();
	}
}