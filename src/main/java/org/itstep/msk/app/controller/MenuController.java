package org.itstep.msk.app.controller;

import org.itstep.msk.app.entity.User;
import org.itstep.msk.app.repository.MenuRepository;
import org.itstep.msk.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/menu")
    private String index(Model model, Principal principal) {
        if (principal != null) {
            User currentUser = userRepository.findByUsername(principal.getName());
            model.addAttribute("user", currentUser);
        } else {
            model.addAttribute("user", null);
        }

        model.addAttribute("roots", menuRepository.getRootCategories());

        return "menu";
    }


//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("roots", menuRepository.getRootCategories());
//
//        return "menu";
//    }
}
