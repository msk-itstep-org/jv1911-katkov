package org.itstep.msk.app.controller;

import org.itstep.msk.app.entity.Dish;
import org.itstep.msk.app.entity.Order;
import org.itstep.msk.app.entity.OrdersDishes;
import org.itstep.msk.app.repository.DishRepository;
import org.itstep.msk.app.repository.OrderRepository;
import org.itstep.msk.app.repository.OrdersDishesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private OrdersDishesRepository ordersDishesRepository;

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
            Principal principal //для того, чтобы сделать имена пользователей
    ) {
        Dish dish = dishRepository.getOne(Long.parseLong(dishId));
        if (dish == null) {
            throw new RuntimeException("Такое блюдо не существует");
        }

        Order order;

        if (orderId.equals("0")) {
            // Создаем заказ и сохраняем в базе данных через репозиторий
            order = new Order();
            order.setWaiterName("Вася");
            orderRepository.save(order);
            orderRepository.flush();

            // Отправляем в браузер куку под названием orderId
            response.addCookie(
                    new Cookie("orderId", order.getId().toString())
            );
        } else {
            order = orderRepository.getOne(
                    Long.parseLong(orderId)
            );
            if (order == null) {
                // Создаем заказ заново
                order = new Order();
                order.setWaiterName("Мустафа");
                orderRepository.save(order);
                orderRepository.flush();

                // Отправляем в браузер куку под названием orderId
                response.addCookie(
                        new Cookie("orderId", order.getId().toString())
                );
            }
        }

        // Помещаем товар с количеством в корзину и сохраняем в базе данных через репозиторий
        OrdersDishes ordersDishes = new OrdersDishes();
        ordersDishes.setOrder(order);
        ordersDishes.setDish(dish);
        ordersDishes.setQuantity(Integer.parseInt(quantity));
        ordersDishesRepository.save(ordersDishes);
        ordersDishesRepository.flush();

        return "redirect:/menu/" + dish.getMenu().getId();
    }
}

