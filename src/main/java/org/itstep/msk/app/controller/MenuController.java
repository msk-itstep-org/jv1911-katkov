package org.itstep.msk.app.controller;

import org.itstep.msk.app.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("roots", menuRepository.getRootCategories());

        return "menu";
    }
}
