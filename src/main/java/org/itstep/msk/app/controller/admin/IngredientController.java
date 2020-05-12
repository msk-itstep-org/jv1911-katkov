package org.itstep.msk.app.controller.admin;

import org.itstep.msk.app.entity.Ingredient;
import org.itstep.msk.app.repository.IngredientRepository;
import org.itstep.msk.app.service.impl.PaginationServiceImpl;
import org.itstep.msk.app.service.impl.ValidationMessagesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/ingredient")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private PaginationServiceImpl paginationService;

    @Autowired
    private ValidationMessagesServiceImpl validationMessagesService;

    @GetMapping("/start")
    public String findAllActiveIngredients(
            @PageableDefault(value = 10, sort = "name")Pageable pageable,
            Model model
    ) {
        Page<Ingredient> ingredients = ingredientRepository.findAllByActiveIsTrue(pageable);
        paginationService.addToModelWithPagination(model, ingredients, pageable);

        return "admin/ingredient/start";
    }

    @GetMapping("/archive")
    public String findAllArchiveIngredients(
            @PageableDefault(value = 10, sort = "name")Pageable pageable,
            Model model
    ) {
        Page<Ingredient> ingredients = ingredientRepository.findAllByActiveIsFalse(pageable);
        paginationService.addToModelWithPagination(model, ingredients, pageable);

        return "admin/ingredient/archive";
    }

    @GetMapping("/add")
    public String adding(Model model){
        Ingredient newIngredient = new Ingredient();
        model.addAttribute("newIngredient", newIngredient);
        model.addAttribute("errors", new HashMap<>());

        return "admin/ingredient/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute Ingredient newIngredient, BindingResult bindingResult, Model model) throws Exception {
        Ingredient sameIngredient = ingredientRepository.findByName(newIngredient.getName());
        if (sameIngredient != null) {
            bindingResult.addError(
                    new FieldError("ingredient", "name", "Такой ингредиент уже существует")
            );
        }

        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errors = new HashMap<>();

            validationMessagesService.createValidationMessages(bindingResult, errors);

            model.addAttribute("errors", errors);
            model.addAttribute("newIngredient", newIngredient);

            return "admin/ingredient/add";
        }

        ingredientRepository.save(newIngredient);
        ingredientRepository.flush();

        return "redirect:/admin/ingredient/start";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable(name = "id") Ingredient ingredient,
            Model model
    ) {
        model.addAttribute("ingredient", ingredient);

        return "admin/ingredient/edit";
    }

    @PostMapping("/save/{id}")
    public String save(
            @PathVariable(name = "id") Ingredient ingredient,
            @ModelAttribute Ingredient editedIngredient
    ) {
        ingredient.setName(editedIngredient.getName());

        ingredientRepository.save(ingredient);
        ingredientRepository.flush();

        return "redirect:/admin/ingredient/edit/" + ingredient.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Ingredient ingredient
    ) {
        if (ingredient == null) {
            throw new RuntimeException("Такого ингредиента не найдено");
        }

        ingredient.setActive(false);

        ingredientRepository.save(ingredient);
        ingredientRepository.flush();

        return "redirect:/admin/ingredient/start";
    }

    @GetMapping("/return/{id}")
    public String retur(@PathVariable(name = "id") Ingredient ingredient
    ) {
        if (ingredient == null) {
            throw new RuntimeException("Такого ингредиента не найдено");
        }

        ingredient.setActive(true);

        ingredientRepository.save(ingredient);
        ingredientRepository.flush();

        return "redirect:/admin/ingredient/archive";
    }

    @GetMapping("/finish-delete/{id}")
    public String finishDelete(@PathVariable(name = "id") Ingredient ingredient
    ) {
        if (ingredient == null) {
            throw new RuntimeException("Такого ингредиента не найдено");
        }

        ingredientRepository.delete(ingredient);
        ingredientRepository.flush();

        return "redirect:/admin/ingredient/archive";
    }
}
