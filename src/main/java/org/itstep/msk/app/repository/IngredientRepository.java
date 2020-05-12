package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Dish;
import org.itstep.msk.app.entity.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Page<Ingredient> findAllByActiveIsTrue(Pageable pageable);

    Page<Ingredient> findAllByActiveIsFalse(Pageable pageable);

    Ingredient findByName(String name);

    List<Ingredient> findAllByActiveIsTrueOrderByName();
}
