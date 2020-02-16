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
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private OrdersDishesRepository ordersDishesRepository;

    @GetMapping("/order")
    public String makeNewOrder(Model model) {
        List<Dish> dishes = dishRepository.findAll();
        model.addAttribute("dishes", dishes);
//        model.addAttribute("orders", orderRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION)));
        return "order";
    }

    @PostMapping("/order/add-to-order")
    public String addToCart(
            @RequestParam String quantity,
            @RequestParam String dishId,
            @CookieValue(
                    value = "orderId",
                    required = false,
                    defaultValue = "0"
            ) String orderId,
            HttpServletResponse response
    ) {
        Dish dish = dishRepository.getOne(Long.parseLong(dishId));
        if (dish == null) {
            throw new RuntimeException("Товар не найден");
        }

        Order order;

        if (orderId.equals("0")) {
            // Создаем заказ и сохраняем в базе данных через репозиторий
            order = new Order();
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

        return "redirect:/";
    }
}

