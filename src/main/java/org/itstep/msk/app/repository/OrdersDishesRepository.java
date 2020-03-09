package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Order;
import org.itstep.msk.app.entity.OrdersDishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDishesRepository extends JpaRepository<OrdersDishes, Long> {
    @Query("SELECT od FROM OrdersDishes od WHERE od.order.id = ?1")
    List<OrdersDishes> findByOrderId(Long orderId);

    @Query("SELECT od FROM OrdersDishes od WHERE od.dish.id = ?1 AND od.order.id = ?2")
    OrdersDishes findByOrderishId(Long dishId, Long orderId);
}
