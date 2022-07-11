package bootstrap.service;

import bootstrap.model.User;

import java.util.List;

public interface UserService {
    public User findUserById(Long userId);
    public List<User> selectAllUsers();
    public void saveUser(User user, String[] roles);
    public void delete(Long id);
    public void update(User user, String[] roles);
    public User getUserByEmailWithRoles(String email);
    public Long count();
}
