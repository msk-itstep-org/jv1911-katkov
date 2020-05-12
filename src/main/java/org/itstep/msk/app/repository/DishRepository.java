package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Dish;
import org.itstep.msk.app.entity.Menu;;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    List<Dish> findAllByMenuOrderByName(Menu menu);

    Page<Dish> findAllByActiveIsTrue(Pageable pageable);

    Page<Dish> findAllByActiveIsFalse(Pageable pageable);

    Dish findByName(String name);
}
