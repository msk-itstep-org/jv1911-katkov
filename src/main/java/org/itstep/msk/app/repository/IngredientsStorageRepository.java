package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Ingredient;
import org.itstep.msk.app.entity.IngredientsStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsStorageRepository extends JpaRepository<IngredientsStorage, Long> {
    List<IngredientsStorage> findAllByIngredient(Ingredient ingredient);

}
