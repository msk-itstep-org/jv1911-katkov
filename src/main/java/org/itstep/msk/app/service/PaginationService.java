package org.itstep.msk.app.service;

import org.itstep.msk.app.entity.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

public interface PaginationService {
    <T> void addToModelWithPagination(Model model, Page<T> items, Pageable pageable);
}
