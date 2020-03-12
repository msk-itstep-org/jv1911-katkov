package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.DishesIngredients;
import org.itstep.msk.app.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishesIngredientsRepository extends JpaRepository<DishesIngredients, Long> {
    @Query("SELECT di FROM DishesIngredients di WHERE dish.id = ?1")
    List<DishesIngredients> ingredientsForDish(Long dishId);


}
