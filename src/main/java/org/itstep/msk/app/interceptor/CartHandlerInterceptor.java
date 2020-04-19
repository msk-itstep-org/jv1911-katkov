package org.itstep.msk.app.interceptor;

import org.itstep.msk.app.entity.OrderDish;
import org.itstep.msk.app.repository.OrderDishRepository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CartHandlerInterceptor extends HandlerInterceptorAdapter {

    private OrderDishRepository orderDishRepository;

    public CartHandlerInterceptor(OrderDishRepository orderDishRepository) {
        this.orderDishRepository = orderDishRepository;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) throws Exception {

        Cookie cookie= WebUtils.getCookie(request, "orderId");
        if (cookie != null) {
            String name = cookie.getName();
            Long orderId = Long.parseLong(cookie.getValue());
            System.out.println(name + " " + orderId);
            System.out.println("----------------");
            List<OrderDish> currentOrderDishList = orderDishRepository.findByOrderId(orderId);
            modelAndView.addObject("cartObjects", currentOrderDishList);
        }
    }
}
