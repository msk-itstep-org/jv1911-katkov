package org.itstep.msk.app.controller;

import org.itstep.msk.app.entity.*;
import org.itstep.msk.app.model.DishCount;
import org.itstep.msk.app.repository.DishRepository;
import org.itstep.msk.app.repository.OrderDishRepository;
import org.itstep.msk.app.repository.OrderRepository;
import org.itstep.msk.app.repository.UserRepository;
import org.itstep.msk.app.service.IngredientsQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private OrderDishRepository orderDishRepository;

    @Autowired
    private IngredientsQuantityService ingredientsQuantityService;

    @GetMapping("/")
    private String index(Model model, Principal principal) {
        List<Order> orders = orderRepository.findAllByActiveIsTrueOrderByOrderDate();

        model.addAttribute("orders", orders);
        model.addAttribute("currentUsername", principal.getName());
        addPopoverWithDishAndQuantity(model);

        return "index";
    }

    @GetMapping("/main/add")
    private String add(Principal principal) {
        if (principal == null) {
            return "login";
        }
        List<Order> orders = orderRepository.findAllByActiveIsTrueOrderByOrderDate();

        Order newOrder = new Order();
        newOrder.setOrderDate(new Date());
        newOrder.setUser(userRepository.findByUsername(principal.getName()));
        orders.add(newOrder);

        orderRepository.save(newOrder);
        orderRepository.flush();

        return "redirect:/";
    }

    @GetMapping("/main/edit/{id}")
    private String editing(@PathVariable(name = "id") Order order, Model model) {

        List<OrderDish> orderDishes = orderDishRepository.findAllByOrder(order);
        List<Dish> dishes = new ArrayList<>();
        Map<Dish, Integer> dishCounts = new HashMap<>();

        for(OrderDish orderDish : orderDishes) {
            dishes.add(orderDish.getDish());
        }

        for (Dish dish : dishes) {
            Integer count = ingredientsQuantityService.countDishesCanCook(dish);
            dishCounts.put(dish, count);
        }

        model.addAttribute("order", order);
        model.addAttribute("orderDishes", orderDishes);
        model.addAttribute("dishCounts", dishCounts);

        return "main/edit";
    }


    @PostMapping("/main/edit-order/{id}")
    private String edit(@PathVariable(name = "id") Order order,
                        @RequestParam String dishId,
                        @RequestParam String quantity
    ) {
        Dish dish = dishRepository.getOne(Long.parseLong(dishId));
        if (dish == null) {
            throw new RuntimeException("Такое блюдо не существует");
        }
        OrderDish orderDish = orderDishRepository.findOneByDishAndOrder(dish, order);
        orderDish.setQuantity(Integer.parseInt(quantity));

        orderDishRepository.save(orderDish);
        orderDishRepository.flush();
        orderRepository.save(order);
        orderRepository.flush();

        return "/main/edit/" + order.getId();
    }

    @GetMapping("/main/pay/{id}")
    String delete(@PathVariable(name = "id") Order order
    ) {
        if (order == null) {
            throw new RuntimeException("Такого заказа не найдено");
        }

        order.setActive(false);

        orderRepository.save(order);
        orderRepository.flush();

        return "redirect:/";
    }

    private void addPopoverWithDishAndQuantity(Model model){
        List<Order> orders = orderRepository.findAllByActiveIsTrueOrderByOrderDate();
        Map<String,List<String>> dishInOrder = new HashMap<>();
        for (Order order : orders) {
            List<String> orderDishes = order.getOrdersDishes()
                    .stream()
                    .map(x -> x.getDish().getName() + " - " + x.getQuantity() + " шт.")
                    .collect(Collectors.toList());

            dishInOrder.put(order.getId().toString(), orderDishes);
        }

        model.addAttribute("order_dishes", dishInOrder);
    }
}
