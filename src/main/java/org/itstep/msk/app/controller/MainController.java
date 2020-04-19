package org.itstep.msk.app.controller;

import org.itstep.msk.app.entity.Order;
import org.itstep.msk.app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private OrderRepository orderRepository;
    @GetMapping("/")
    private String index(Model model) {

        return "/index";
    }
}
