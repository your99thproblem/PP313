package bootstrap.dao;

import bootstrap.model.User;

import java.util.List;

public interface UserDao {
    public void save(User user, String[] roles);
    public User update(User user, String[] roles);
    List<User> findAll();
    User findById(Long id);
    void detete(Long id);
    public User getUserByEmailWithRoles(String email);
    public Long count();
}
