package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Ingredient;
import org.itstep.msk.app.entity.IngredientStorage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientStorageRepository extends JpaRepository<IngredientStorage, Long> {
    List<IngredientStorage> findAllByIngredient(Ingredient ingredient);
}
