package bootstrap.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity(name = "User")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Email
    @Column(unique = true)
    @NotBlank(message = "Enter your email")
    private String email;
    @NotBlank(message = "Enter your password")
    @Size(min = 2)
    private String password;
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<UserRole> userRoleList = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Set<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(Set<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public void addRole(Role role) {
        UserRole userRole = new UserRole(this, role);
        userRoleList.add(userRole);
        role.getUsers().add(userRole);
    }
}
