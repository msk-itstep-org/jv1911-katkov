package org.itstep.msk.app.controller;

import org.itstep.msk.app.entity.Dish;
import org.itstep.msk.app.entity.Order;
import org.itstep.msk.app.entity.OrderDish;
import org.itstep.msk.app.entity.User;
import org.itstep.msk.app.model.IngrAndQuantity;
import org.itstep.msk.app.repository.DishRepository;
import org.itstep.msk.app.repository.OrderRepository;
import org.itstep.msk.app.repository.OrderDishRepository;
import org.itstep.msk.app.repository.UserRepository;
import org.itstep.msk.app.service.IngredientsQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderDishRepository orderDishRepository;

    @Autowired
    private IngredientsQuantityService ingredientsQuantityService;

    @PostMapping("/add-to-order")
    public String addToOrder(
            @RequestParam String dishId,
            @RequestParam String quantity,
            @CookieValue(
                    value = "orderId",
                    required = false,
                    defaultValue = "0"
            ) String orderId,
            HttpServletResponse response,
            Principal principal
    ) {
        Dish dish = dishRepository.getOne(Long.parseLong(dishId));
        if (dish == null) {
            throw new RuntimeException("Такое блюдо не существует");
        }

        Order order;
        System.out.println("Cookie First = " + orderId);
        System.out.println("Principal = " + principal.getName());

        if (orderId.equals("0")) {
            // Создаем заказ и сохраняем в базе данных через репозиторий
            User user = userRepository.findByUsername(principal.getName());
            order = createAndSaveOrder(user);

            // Отправляем в браузер куку под названием orderId
            response.addCookie(
                    new Cookie("orderId", order.getId().toString())
            );
//            response.addCookie(
//                    new Cookie("orderId", ""));
            System.out.println("orderId.equals(0)");
        } else {
            order = orderRepository.getOne(Long.parseLong(orderId));
            System.out.println("orderId in ELSE First order.getId() = " + order.getId());
            System.out.println("Cookie ELSE = " + orderId);

            if (order != null) {
//                    && (order.getUser() == null
//                    || order.getOrderDate() == null)) {
                User user = userRepository.findByUsername(principal.getName());
                order.setUser(user);
                order.setOrderDate(new Date());
                System.out.println("orderId else");
                System.out.println("orderId in ELSE order.getId() = " + order.getId());
                System.out.println("orderId in ELSE order.getUserName() = " + order.getUser().getUsername());
                System.out.println("orderId in ELSE order.getIOrderDate() = " + order.getOrderDate());
                System.out.println("Cookie ELSE SECOND = " + orderId);
            }

            if (order == null) {
                // Создаем заказ заново
                User user = userRepository.findByUsername(principal.getName());
                order = createAndSaveOrder(user);
                System.out.println("in IF == NULL + order.getId() = " + order.getId());
                // Отправляем в браузер куку под названием orderId
                response.addCookie(
                        new Cookie("orderId", order.getId().toString())
                );
            }
        }

        // Помещаем товар с количеством в корзину и сохраняем в базе данных через репозиторий
        OrderDish orderDish = new OrderDish();
        orderDish.setOrder(order);
        System.out.println("orderId in SAVE order.getId() = " + order.getId());
        System.out.println("orderId in SAVE order.getUserName() = " + order.getUser().getUsername());
        System.out.println("orderId in SAVE order.getIOrderDate() = " + order.getOrderDate());
        orderDish.setDish(dish);
        orderDish.setQuantity(Integer.parseInt(quantity));
        orderRepository.save(order);
        orderRepository.flush();
        orderDishRepository.save(orderDish);
        orderDishRepository.flush();
        //нашел какие ингредиенты и сколько есть в блюде
//        List<IngrAndQuantity> ingrAndQuantityList = ingredientsQuantityService
//                .countIngredientsQuantityRest(orderDish.getDish());

        ingredientsQuantityService.removeIngredientsFromStorage(
                orderDish.getDish(),
                Integer.parseInt(quantity));
        System.out.println("orderId cookie = " + orderId);
        System.out.println("orderId order.getId() = " + order.getId());

        return "redirect:/menu/" + dish.getMenu().getId();
    }

    private Order createNewOrder(User user) {
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());

        return order;
    }

    private Order createAndSaveOrder(User user) {
        Order order = createNewOrder(user);
        orderRepository.save(order);
        orderRepository.flush();

        return order;
    }


}



