package org.itstep.msk.app.controller;

import org.itstep.msk.app.entity.*;
import org.itstep.msk.app.model.DishCount;
import org.itstep.msk.app.repository.*;
import org.itstep.msk.app.service.impl.IngredientsQuantityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private IngredientsQuantityServiceImpl ingredientsQuantityService;

    @Autowired
    private OrderDishRepository orderDishRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/menu/{id}")
    private String mainMenu(@PathVariable(name = "id") Order order, Model model) {

        model.addAttribute("roots", menuRepository.getRootCategories());
        model.addAttribute("order", order);

        return "menu";
    }

    @GetMapping("/menu/{orderId}/{menuId}")
    private String addDish(
            Model model,
            @PathVariable(name = "orderId") Order order,
            @PathVariable(name = "menuId") Menu menu
        ) {
        List<Dish> dishes = dishRepository.findAllByMenuOrderByName(menu);
        Map<Dish, Integer> dishCounts = countDishCanCookPart(dishes);

        model.addAttribute("dishes", dishes);
        model.addAttribute("menu", menu);
        model.addAttribute("dishCounts", dishCounts);
        model.addAttribute("order", order);

        return "main/order";
    }

        @PostMapping("/menu/add-to-order/{id}")
    private String addToOrder(
            @PathVariable(name = "id") Order order,
            @RequestParam String dishId,
            @RequestParam String quantity
    ) {
        Dish dish = dishRepository.getOne(Long.parseLong(dishId));
        if (dish == null) {
            throw new RuntimeException("Такое блюдо не существует");
        }

        List<OrderDish> orderDishes = order.getOrdersDishes();

        for (OrderDish orderDish : orderDishes) {
            if (orderDish.getDish().getName().equals(dish.getName())) {
                orderDish.setQuantity(orderDish.getQuantity() + Integer.parseInt(quantity));
                saveOrderAndOrderDish(order, orderDish, quantity);

                return "redirect:/menu/" + order.getId() + "/" + dish.getMenu().getId();
            }
        }

        OrderDish orderDish = new OrderDish();
        orderDish.setOrder(order);
        orderDish.setDish(dish);
        orderDish.setQuantity(Integer.parseInt(quantity));
        saveOrderAndOrderDish(order, orderDish, quantity);

        return "redirect:/menu/" + order.getId() + "/" + dish.getMenu().getId();
    }

    private Map<Dish, Integer> countDishCanCookPart(List<Dish> dishes) {
        Map<Dish, Integer> dishCounts = new LinkedHashMap<>();

        for (Dish dish : dishes) {
            Integer count = ingredientsQuantityService.countDishesCanCook(dish);
            dishCounts.put(dish, count);
        }

        return dishCounts;
    }

    private void saveOrderAndOrderDish(Order order, OrderDish orderDish, String quantity) {
        orderDishRepository.save(orderDish);
        orderDishRepository.flush();
        orderRepository.save(order);
        orderRepository.flush();

        ingredientsQuantityService.removeIngredientsFromStorage(
                orderDish.getDish(),
                Integer.parseInt(quantity));
    }
}
