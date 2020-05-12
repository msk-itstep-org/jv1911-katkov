package org.itstep.msk.app.controller.manager;

import org.itstep.msk.app.entity.*;
import org.itstep.msk.app.repository.*;
import org.itstep.msk.app.service.IngredientsQuantityService;
import org.itstep.msk.app.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaginationService paginationService;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientStorageRepository ingredientStorageRepository;

    @GetMapping("/start")
    private String findAllIngredientStorage(
            @PageableDefault(value = 10, sort = "receiptDate") Pageable pageable,
            Model model
    ) {
        Page<IngredientStorage> ingredientStorages = ingredientStorageRepository.findAll(pageable);
        paginationService.addToModelWithPagination(model, ingredientStorages, pageable);

        return "manager/start";
    }

    @GetMapping("/archive")
    private String archive(
            @PageableDefault(value = 5, sort = "orderDate") Pageable pageable,
            Model model
    ) {
        Page<Order> orders = orderRepository.findAllByActiveIsFalseOrderByOrderDate(pageable);
        paginationService.addToModelWithPagination(model, orders, pageable);

        return "manager/archive";
    }

    @GetMapping("/add")
    private String addingNewIngredient(Model model) {
        List<Ingredient> ingredients = ingredientRepository.findAllByActiveIsTrueOrderByName();
        IngredientStorage newIngredientStorage = new IngredientStorage();

        model.addAttribute("newIngredientStorage", newIngredientStorage);
        model.addAttribute("ingredients", ingredients);

        return "manager/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute IngredientStorage newIngredientStorage
    ) {
        newIngredientStorage.setQuantity(newIngredientStorage.getQuantity() * 1000);

        ingredientStorageRepository.save(newIngredientStorage);
        ingredientRepository.flush();

        return "redirect:/manager/add";
    }

    @GetMapping("/delete/{id}")
    public String finishDelete(@PathVariable(name = "id") IngredientStorage ingredientStorage
    ) {
        if (ingredientStorage == null) {
            throw new RuntimeException("Такого продукта не найдено");
        }

        ingredientStorageRepository.delete(ingredientStorage);
        ingredientStorageRepository.flush();

        return "redirect:/manager/start";
    }
}
