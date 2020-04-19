package org.itstep.msk.app.model;

import org.itstep.msk.app.entity.Dish;

public class DishCount {

    private Dish dish;
    private Integer count;

    public DishCount(Dish dish, Integer count) {
        this.dish = dish;
        this.count = count;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
