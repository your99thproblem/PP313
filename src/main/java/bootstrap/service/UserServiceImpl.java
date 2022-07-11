package bootstrap.service;

import bootstrap.dao.RoleDao;
import bootstrap.dao.UserDao;
import bootstrap.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;


    @Override
    public User findUserById(Long userId) {
        return userDao.findById(userId);
    }

    @Override
    public List<User> selectAllUsers() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void saveUser(User user, String[] roles) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user, roles);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.detete(id);
    }

    @Override
    @Transactional
    public void update(User user, String[] roles) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.update(user, roles);
    }

    @Override
    public User getUserByEmailWithRoles(String email) {
        return userDao.getUserByEmailWithRoles(email);
    }
    public Long count() {
        return userDao.count();
    }
}
