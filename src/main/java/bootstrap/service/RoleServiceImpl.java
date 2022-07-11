package bootstrap.service;

import bootstrap.dao.RoleDao;
import bootstrap.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> selectAllRoles() {
        return roleDao.findAll();
    }
}
