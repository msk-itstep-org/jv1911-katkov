//package org.itstep.msk.app.controller;
//
//import org.itstep.msk.app.entity.Dish;
//import org.itstep.msk.app.entity.Order;
//import org.itstep.msk.app.repository.OrderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class OrderController {
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @GetMapping("/order")
//    public String makeNewOrder(Model model) {
//        Dish newDish = new Dish();
//        model.addAttribute("newOrder", newDish);
////        model.addAttribute("orders", orderRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION)));
//
//        return "order";
//    }
//}
//
