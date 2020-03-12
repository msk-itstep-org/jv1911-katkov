package org.itstep.msk.app.controller;

import org.itstep.msk.app.entity.Role;
import org.itstep.msk.app.entity.User;
import org.itstep.msk.app.repository.RoleRepository;
import org.itstep.msk.app.repository.UserRepository;
import org.itstep.msk.app.service.MyBCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MyBCryptPasswordEncoder myBCryptPasswordEncoder;

    @GetMapping("/registration")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);

        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(@ModelAttribute User user) {
        user.setPassword(
                myBCryptPasswordEncoder.encode(user.getPassword())
        );

        Role roleUser = roleRepository.findById(1L).get();
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        user.setRoles(roles);

        userRepository.save(user);
        userRepository.flush();

        return "login";
    }
}
