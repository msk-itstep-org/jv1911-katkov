package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Ingredient;
import org.itstep.msk.app.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    @Query("SELECT s FROM Storage s WHERE s.ingredients = ?1")
    List<Storage> findIngrQuantityInStorage(List<Ingredient> ingredients);
}
