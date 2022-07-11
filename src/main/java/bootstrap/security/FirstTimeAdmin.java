package bootstrap.security;

import bootstrap.model.Role;
import bootstrap.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class FirstTimeAdmin {

    public static User getFirstTimeUser(String email) {
        User defaultUser = new User();
        Role adminRole = new Role();
        Role userRole = new Role();
        adminRole.setId(0L);
        adminRole.setName("ROLE_ADMIN");
        userRole.setId(1L);
        userRole.setName("ROLE_USER");
        defaultUser.addRole(adminRole);
        defaultUser.addRole(userRole);
        defaultUser.setId(0L);
        defaultUser.setEmail("admin");
        defaultUser.setName("Administrator");
        defaultUser.setPassword(new BCryptPasswordEncoder().encode("admin"));
        if(defaultUser.getEmail().equals(email)) {
            return defaultUser;
        } else return null;
    }
}
