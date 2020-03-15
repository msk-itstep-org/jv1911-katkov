package org.itstep.msk.app.service;

import org.itstep.msk.app.model.IngrAndQuantity;

import java.util.List;

public interface IngredientsQuantityService {
    List<IngrAndQuantity> countIngredientsQuantityRest(Long dishId);

}
