package app.dao.impl;


import app.dao.interfaces.UserDao;
import app.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;



@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	public User getUserByUsername(String name) {
        User user = (User) em.createQuery("SELECT u FROM User u WHERE u.name =:username").setParameter("username", name).getSingleResult();
        return user;
	}

	@Override
	public void persist(User entity) {
	    em.persist(entity);
	}

	@Override
	public User getByKey(Long id) {
		return em.find(User.class,id);
	}

	@Override
	public List<User> getAll() {
       return em.createQuery("FROM User").getResultList();

	}

	@Override
	public void update(User user) {
        em.merge(user);
	}

	@Override
	public void deleteByKey(Long id) {
        User entity =  em.find(User.class, id);
        em.remove(entity);
	}
}
