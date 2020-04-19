//package org.itstep.msk.app.config;
//
//import org.itstep.msk.app.interceptor.CartHandlerInterceptor;
//import org.itstep.msk.app.repository.OrderDishRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfigure implements WebMvcConfigurer {
//    @Autowired
//    OrderDishRepository orderDishRepository;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new CartHandlerInterceptor(orderDishRepository));
//    }
//}
