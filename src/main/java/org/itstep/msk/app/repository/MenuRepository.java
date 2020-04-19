package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.Dish;
import org.itstep.msk.app.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("SELECT m FROM Menu m WHERE m.parent IS NULL")
    List<Menu> getRootCategories();

    @Query("SELECT m " +
            "FROM Menu m " +
            "WHERE m.parent IS NOT NULL OR m.parent NOT BETWEEN 1 AND 3 " +
            "ORDER BY  m.name")
    List<Menu> findAllInFinishState();


}
