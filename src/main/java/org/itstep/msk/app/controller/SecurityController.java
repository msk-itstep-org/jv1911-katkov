package org.itstep.msk.app.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class SecurityController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/denied")
    public String denied() {
        return "denied";
    }

}
