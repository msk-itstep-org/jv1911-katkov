package org.itstep.msk.app.controller;

import org.itstep.msk.app.entity.User;
import org.itstep.msk.app.enums.Role;
import org.itstep.msk.app.repository.UserRepository;
import org.itstep.msk.app.service.MyBCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyBCryptPasswordEncoder myBCryptPasswordEncoder;

    @GetMapping("/login")
    public String login(@RequestParam(defaultValue = "false") String error, Model model) {
        model.addAttribute("error", error.equalsIgnoreCase("true"));

        return "login";
    }

    @GetMapping("denied")
    public String denied() {
        return "denied";
    }
}
