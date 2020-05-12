package org.itstep.msk.app.controller;

import org.itstep.msk.app.entity.Dish;
import org.itstep.msk.app.entity.Menu;
import org.itstep.msk.app.entity.User;
import org.itstep.msk.app.model.DishCount;
import org.itstep.msk.app.repository.DishRepository;
import org.itstep.msk.app.repository.MenuRepository;
import org.itstep.msk.app.repository.UserRepository;
import org.itstep.msk.app.service.impl.IngredientsQuantityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private IngredientsQuantityServiceImpl ingredientsQuantityService;

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
    private String giveTypeOfDish(@PathVariable(name = "id") Menu menu, Model model) {
        List<DishCount> dishCounts = new ArrayList<>();
        List<Dish> dishes = dishRepository.findAllByMenu(menu);
        for (Dish dish : dishes) {
            Integer count = ingredientsQuantityService.countDishesCanCook(dish);
            dishCounts.add(new DishCount(dish, count));
        }

        model.addAttribute("dishes", dishes);
        model.addAttribute("menu", menu);
        model.addAttribute("dishCounts", dishCounts);

        return "order";
    }
}

//    @GetMapping("/menu/{id}")
//    private String giveTypeOfDish(@PathVariable(name = "id") Long menuId, Model model) {
//        List<DishCount> dishCounts = new ArrayList<>();
//        List<Dish> dishes = dishRepository.findAllDishFromMenu(menuId);
//        for (Dish dish : dishes) {
//            Integer count = ingredientsQuantityService.countDishesCanCook(dish.getId());
//            dishCounts.add(new DishCount(dish, count));
//        }
//
//        Menu menu = menuRepository.findById(menuId).get();
//        //TODO
//
//
//        model.addAttribute("dishes", dishes);
//        model.addAttribute("menu", menu);
//        model.addAttribute("dishCounts", dishCounts);
//
//        return "order";
//    }
//}

