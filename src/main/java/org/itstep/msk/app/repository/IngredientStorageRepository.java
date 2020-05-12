package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Ingredient;
import org.itstep.msk.app.entity.IngredientStorage;
import org.itstep.msk.app.entity.OrderDish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientStorageRepository extends JpaRepository<IngredientStorage, Long> {
    List<IngredientStorage> findAllByIngredientOrderByReceiptDate(Ingredient ingredient);

//    @Query("SELECT is FROM IngredientStorage is WHERE is.ingredient = ?1 " +
//            "AND is.quantity > 0 " +
//            "ORDER BY is.receiptDate")
//    List<IngredientStorage> findAllByIngredientAndUsed(Ingredient ingredient);
}
