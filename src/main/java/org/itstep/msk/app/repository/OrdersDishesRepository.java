package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.OrdersDishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDishesRepository extends JpaRepository<OrdersDishes, Long> {
}
