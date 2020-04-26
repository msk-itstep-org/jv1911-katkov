package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Dish;
import org.itstep.msk.app.entity.Order;
import org.itstep.msk.app.entity.OrderDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDishRepository extends JpaRepository<OrderDish, Long> {
    @Query("SELECT od FROM OrderDish od WHERE od.order.id = ?1")
    List<OrderDish> findByOrderId(Long orderId);

    @Query("SELECT od FROM OrderDish od WHERE od.dish.id = ?1 AND od.order.id = ?2")
    OrderDish findByOrderId(Long dishId, Long orderId);

    List<OrderDish> findByOrder(Order order);

    OrderDish findOneByDishAndOrder(Dish dish, Order order);

    List<OrderDish> findAllByOrder(Order order);
}
