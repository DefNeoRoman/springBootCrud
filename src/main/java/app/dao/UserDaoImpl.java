package app.dao;


import app.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository
public class UserDaoImpl implements UserDao {

	private final SessionFactory sessionFactory;

	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public User getUserByUsername(String name) {
		Query query= sessionFactory.getCurrentSession().
				createQuery("from User where name=:name");
		query.setParameter("name", name);
		return (User) query.uniqueResult();
	}

	@Override
	public void persist(User entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public User getByKey(Long id) {
		return sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public List<User> getAll() {
		return null;
	}

	@Override
	public void update(User group) {

	}

	@Override
	public void deleteByKey(Long id) {

	}
}
