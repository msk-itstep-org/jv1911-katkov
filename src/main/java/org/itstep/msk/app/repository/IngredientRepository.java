package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Dish;
import org.itstep.msk.app.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
//    @Query("SELECT i FROM Ingredient i WHERE dishesIngredients.dish.id = ?1")
//    List<Ingredient> ingredientsForDish(Long dishId);
}
