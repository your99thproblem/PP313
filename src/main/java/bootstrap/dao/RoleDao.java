package bootstrap.dao;

import bootstrap.model.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> findAll();

    public Role findRoleById(Long id);
}
