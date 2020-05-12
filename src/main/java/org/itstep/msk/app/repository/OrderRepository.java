package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findFirstById(Long id);

    List<Order> findAllByActiveIsTrueOrderByOrderDate();
}
