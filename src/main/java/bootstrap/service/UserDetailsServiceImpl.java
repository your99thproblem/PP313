package bootstrap.service;

import bootstrap.dao.UserDao;
import bootstrap.model.CustomUserDetails;
import bootstrap.model.User;
import bootstrap.model.UserRole;
import bootstrap.security.FirstTimeAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws BadCredentialsException {

        User currentUser = userDao.getUserByEmailWithRoles(email);

        if (currentUser != null) {

            Set<GrantedAuthority> authorities = new HashSet<>();
            for (UserRole roles : currentUser.getUserRoleList()) {
                authorities.add(new SimpleGrantedAuthority(roles.getRole().getName()));
            }
            CustomUserDetails customUser = new CustomUserDetails();
            customUser.setUser(currentUser);
            customUser.setAuthorities(authorities);
            return customUser;

        } else {
            if (userDao.count() == 0) {
                User defaultUser = FirstTimeAdmin.getFirstTimeUser(email);
                if (defaultUser == null) {
                    throw new BadCredentialsException(String.format("Log in with default credentials"));
                } else {
                    CustomUserDetails root = new CustomUserDetails();
                    root.setUser(defaultUser);
                    root.setAuthorities(defaultUser.getUserRoleList().stream().map(r -> new SimpleGrantedAuthority(r.getRole().getName())).collect(Collectors.toSet()));
                    return root;
                }
            } else {
                throw new BadCredentialsException(String.format("User with email '%s' not found", email));
            }
        }
    }
    public static boolean hasAdminRole (CustomUserDetails user) {
        return user.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));}
}
