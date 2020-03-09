package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Dish;
import org.itstep.msk.app.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    @Query("SELECT d FROM Dish d WHERE d.menu.id = ?1 ORDER BY d.name")
    List<Dish> findAllDishFromMenu(Long id);
}
