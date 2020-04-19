package org.itstep.msk.app.controller;

import org.itstep.msk.app.entity.Order;
import org.itstep.msk.app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/")
    private String index(Model model) {
        List<Order> orders = orderRepository.findAllByActiveIsTrueOrderByOrderDate();
        model.addAttribute("orders", orders);

        return "/index";
    }

    @GetMapping("/add")
    private String add(Model model) {
        Order order = new Order();
        order.setOrderDate(new Date());

        model.addAttribute("order", order);

        return "/add";
    }
}
