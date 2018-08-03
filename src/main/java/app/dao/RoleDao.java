package app.dao;


import app.entity.Role;

public interface RoleDao extends GenericDao<Long,Role> {

	Role getRoleByRoleName(String roleName);

}