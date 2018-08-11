package app.dao;


import app.model.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserDao extends GenericDao<Long,User>{
    User getUserByUsername(String username);
}