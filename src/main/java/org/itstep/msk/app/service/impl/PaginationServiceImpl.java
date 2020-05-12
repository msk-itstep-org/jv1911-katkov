package org.itstep.msk.app.service.impl;

import org.itstep.msk.app.entity.Ingredient;
import org.itstep.msk.app.service.PaginationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.stream.IntStream;

@Service
public class PaginationServiceImpl implements PaginationService {
    @Override
    public <T> void addToModelWithPagination (Model model, Page<T> items, Pageable pageable) {
        model.addAttribute("items", items.getContent());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("pages", items.getTotalPages());
        model.addAttribute("pagesRange", IntStream.range(0, items.getTotalPages()).toArray());
        model.addAttribute("pageValue", pageable.getPageSize());
    }
}
