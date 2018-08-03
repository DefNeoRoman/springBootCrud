package app.dao;


import app.entity.User;

public interface UserDao extends GenericDao<Long,User>{
    User getUserByUsername(String username);
}