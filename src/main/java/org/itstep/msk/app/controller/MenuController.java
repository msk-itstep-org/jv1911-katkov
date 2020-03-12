package org.itstep.msk.app.controller;

import org.itstep.msk.app.entity.Dish;
import org.itstep.msk.app.entity.Menu;
import org.itstep.msk.app.entity.User;
import org.itstep.msk.app.repository.DishRepository;
import org.itstep.msk.app.repository.MenuRepository;
import org.itstep.msk.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DishRepository dishRepository;

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

    @GetMapping("/menu/{id}")
    private String giveTypeOfDish(@PathVariable(name = "id") Long menuId, Model model) {
        List<Dish> dishes = dishRepository.findAllDishFromMenu(menuId);
//        List<Dish> dishes = dishRepository.findAll().stream()
//                .filter(x -> x.getMenu().getId().equals(menuId))
//                .collect(Collectors.toList());

        Menu menu = menuRepository.findById(menuId).get();
        //TODO

        model.addAttribute("dishes", dishes);
        model.addAttribute("menu", menu);

        return "order";
    }
}

