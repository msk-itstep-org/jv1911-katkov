package org.itstep.msk.app.controller.admin;

import org.itstep.msk.app.entity.*;
import org.itstep.msk.app.repository.*;
import org.itstep.msk.app.service.impl.PaginationServiceImpl;
import org.itstep.msk.app.service.impl.ValidationMessagesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/dish")
public class DishController {
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private DishIngredientRepository dishIngredientRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UploadRepository uploadRepository;

    @Value("${app.uploads.path}")
    private String uploadsPath;

    @Autowired
    private PaginationServiceImpl paginationService;

    @Autowired
    private ValidationMessagesServiceImpl validationMessagesService;

    @GetMapping("/start")
    public String findAllActiveDishes(
            @PageableDefault(value = 4, sort = "menu.name")Pageable pageable,
            Model model
    ) {
        Page<Dish> dishes = dishRepository.findAllByActiveIsTrue(pageable);
        paginationService.addToModelWithPagination(model, dishes, pageable);
        addPopoverWithIngredients(model);

        return "admin/dish/start";
    }

    @GetMapping("/archive")
    public String findAllArchiveDishes(
            @PageableDefault(value = 4, sort = "menu.name")Pageable pageable,
            Model model
    ) {
        Page<Dish> dishes = dishRepository.findAllByActiveIsFalse(pageable);
       paginationService.addToModelWithPagination(model, dishes, pageable);
        addPopoverWithIngredients(model);

        return "admin/dish/archive";
    }

    @GetMapping("/add")
    public String adding(Model model) {
        Dish newDish = new Dish();
        List<Menu> menu = menuRepository.findAllInFinishState();
        model.addAttribute("newDish", newDish);
        model.addAttribute("menu", menu);
        model.addAttribute("errors", new HashMap<>());

        return "admin/dish/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute Dish newDish, BindingResult bindingResult, Model model
    ) {
        Dish sameName = dishRepository.findByName(newDish.getName());
        if (sameName != null) {
            bindingResult.addError(
                    new FieldError("dish", "name", "Блюдо с таким именем уже существует")
            );
        }

        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errors = new HashMap<>();
            validationMessagesService.createValidationMessages(bindingResult, errors);

            model.addAttribute("errors", errors);
            model.addAttribute("newDish", newDish);

            return "admin/dish/add";
        }

        dishRepository.save(newDish);
        dishRepository.flush();

        return "redirect:/admin/dish/edit/" + newDish.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable(name = "id") Dish dish,
            Model model
    ) {
        DishIngredient newDishIngredient = new DishIngredient();
        List<Ingredient> ingredients = ingredientRepository.findAllByActiveIsTrueOrderByName();
        model.addAttribute("dish", dish);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("newDishIngredient", newDishIngredient);

        return "admin/dish/edit";
    }

    @PostMapping("/save/{id}")
    public String save(
            @PathVariable(name = "id") Dish dish,
            @ModelAttribute Dish editedDish
    ) {
        dish.setName(editedDish.getName());
        dish.setCost(editedDish.getCost());
        dish.setDescription(editedDish.getDescription());
        dish.setActive(editedDish.getActive());

        dishRepository.save(dish);
        dishRepository.flush();

        return "redirect:/admin/dish/start/";
    }

    @PostMapping("/photo/{id}")
    public String getPhoto(
            @PathVariable(name = "id") Dish dish,
            @RequestParam("file") MultipartFile file)
            throws IOException
    {
        String filename = (UUID.randomUUID()).toString();
        Path path = Paths.get(uploadsPath).toAbsolutePath().resolve(filename);

        file.transferTo(path.toFile());
        System.out.println(path.toFile());

        Upload upload = new Upload();
        upload.setFilename(filename);
        upload.setOriginalFilename(file.getOriginalFilename());
        upload.setContentType(file.getContentType());

        System.out.println(filename);
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());

        uploadRepository.save(upload);
        uploadRepository.flush();

        dish.setDishPhoto(upload);
        dishRepository.save(dish);
        dishRepository.flush();

        return "redirect:/admin/dish/edit/" + dish.getId();
    }

    @PostMapping("/save-ingredient/{id}")
    public String saveIngredient(
            @PathVariable(name = "id") Dish dish,
            @ModelAttribute DishIngredient newDishIngredient
    ) {
        List<DishIngredient> dishIngredients = dish.getDishesIngredients();
        dishIngredients.add(newDishIngredient);
        dish.setDishesIngredients(dishIngredients);
        newDishIngredient.setDish(dish);

        dishIngredientRepository.save(newDishIngredient);
        dishIngredientRepository.flush();

        dishRepository.save(dish);
        dishRepository.flush();

        return "redirect:/admin/dish/edit/" + dish.getId();
    }

    @GetMapping("/delete-ingredient/{id}")
    public String deleteIngredient(
            @PathVariable(name = "id") DishIngredient dishIngredient
    ) {
        dishIngredientRepository.delete(dishIngredient);
        dishIngredientRepository.flush();

        return "redirect:/admin/dish/edit/" + dishIngredient.getDish().getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Dish dish
    ) {
        if (dish == null) {
            throw new RuntimeException("Такого блюда не найдено");
        }

        dish.setActive(false);

        dishRepository.save(dish);
        dishRepository.flush();

        return "redirect:/admin/dish/start";
    }

    @GetMapping("/return/{id}")
    public String retur(@PathVariable(name = "id") Dish dish
    ) {
        if (dish == null) {
            throw new RuntimeException("Такого блюда не найдено");
        }

        dish.setActive(true);

        dishRepository.save(dish);
        dishRepository.flush();

        return "redirect:/admin/dish/archive";
    }

    @GetMapping("/finish-delete/{id}")
    public String finishDelete(@PathVariable(name = "id") Dish dish
    ) {
        if (dish == null) {
            throw new RuntimeException("Такого блюда не найдено");
        }

        dishRepository.delete(dish);
        dishRepository.flush();

        return "redirect:/admin/dish/archive";
    }

    private void addPopoverWithIngredients(Model model){
        List<Dish> dishes = dishRepository.findAll();
        Map<String,List<String>> ingrInDish = new HashMap<>();
        for (Dish dish : dishes) {
            List<String> dishIngredients = dish.getDishesIngredients()
                    .stream()
                    .map(x -> x.getIngredient().getName().toString())
                    .collect(Collectors.toList());

            ingrInDish.put(dish.getId().toString(), dishIngredients);
        }

        model.addAttribute("dish_ingredients", ingrInDish);
    }
}
