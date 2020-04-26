package org.itstep.msk.app.controller;

import org.itstep.msk.app.entity.OrderDish;
import org.itstep.msk.app.entity.User;
import org.itstep.msk.app.repository.OrderDishRepository;
import org.itstep.msk.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

//@Controller
//public class CartController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private OrderDishRepository orderDishRepository;
//
//    @GetMapping("/cart")
//    private String cart(
//            Model model,
//            Principal principal,
//            @CookieValue(
//                    value = "orderId",
//                    required = false,
//                    defaultValue = "0"
//            ) String orderId) {
//
//        if (principal != null) {
//            User user = userRepository.findByUsername(principal.getName());
//            model.addAttribute("user", user);
//        } else {
//            model.addAttribute("user", null);
//        }
//
//        List<OrderDish> ordersDishes = orderDishRepository.findByOrderId(Long.parseLong(orderId));
//
//        model.addAttribute("currentCart", ordersDishes);
//
//        return "cart";
//    }
//
//    @DeleteMapping("/delete-dish/{id}")
//    @ResponseBody
//    private String deleteDish(@PathVariable(name = "id") Long dishId,
//                              @CookieValue(
//                                      value = "orderId",
//                                      required = false,
//                                      defaultValue = "0"
//                              ) String orderId) {
//        OrderDish orderDish = orderDishRepository.findByOrderId(dishId, Long.parseLong(orderId));
//
//        if (orderDish == null) {
//            throw new RuntimeException("Нет такого");
//        }
//
//        orderDishRepository.delete(orderDish);
//        orderDishRepository.flush();
//
//        return "ok";
//    }
//}
