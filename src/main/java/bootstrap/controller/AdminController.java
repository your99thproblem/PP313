package bootstrap.controller;

import bootstrap.model.CustomUserDetails;
import bootstrap.model.User;
import bootstrap.service.RoleService;
import bootstrap.service.UserDetailsServiceImpl;
import bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/admin")
    public ModelAndView home() {
        User user1 = new User();
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView mav = new ModelAndView("mainpage");
        mav.addObject("listUser", userService.selectAllUsers());
        mav.addObject("currentUser", currentUser);
        mav.addObject("listRoles", roleService.selectAllRoles());
        mav.addObject("newuser", user1);
        mav.addObject("hasAdminRole", UserDetailsServiceImpl.hasAdminRole(currentUser));
        return mav;
    }
    @PostMapping("/admin/newuser")
    public String addUser(@ModelAttribute("newuser") User user,
                          @RequestParam("rolesToUser") String[] rolesToUsers) {
        System.out.println("check");
        userService.saveUser(user, rolesToUsers);
        return "redirect:/admin";
    }
    @PostMapping("/admin/edituser")
    public String editUser(@ModelAttribute("user") User user,
                          @RequestParam("rolesToUser") String[] rolesToUsers) {
        userService.update(user, rolesToUsers);
        return "redirect:/admin";
    }

    @PostMapping("/admin/deleteuser")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.delete(user.getId());
        return "redirect:/admin";
    }

}
