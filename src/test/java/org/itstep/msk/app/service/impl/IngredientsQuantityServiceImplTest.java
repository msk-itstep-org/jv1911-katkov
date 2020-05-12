package org.itstep.msk.app.service.impl;

import org.itstep.msk.app.entity.Dish;
import org.itstep.msk.app.entity.DishIngredient;
import org.itstep.msk.app.entity.Ingredient;
import org.itstep.msk.app.entity.IngredientStorage;
import org.itstep.msk.app.model.IngrAndQuantity;
import org.itstep.msk.app.repository.DishIngredientRepository;
import org.itstep.msk.app.repository.IngredientStorageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientsQuantityServiceImplTest {
    private List<DishIngredient> dishIngredients;
    private List<IngredientStorage> ingredientStorages;
    private Integer expectedIngredientCount;
    private Integer expectedDishCount;

    public IngredientsQuantityServiceImplTest(
            List<DishIngredient> dishIngredients,
            List<IngredientStorage> ingredientStorages,
            Integer expectedIngredientCount,
            Integer expectedDishCount
    ) {
        this.dishIngredients = dishIngredients;
        this.ingredientStorages = ingredientStorages;
        this.expectedIngredientCount = expectedIngredientCount;
        this.expectedDishCount = expectedDishCount;
    }

    @Parameterized.Parameters
        public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();

        Ingredient ingredient = new Ingredient();
        ingredient.setName("Котлета");

        Dish dish = new Dish();
        dish.setName("Бургер");
        dish.setActive(true);

        DishIngredient dishIngredient = new DishIngredient();
        dishIngredient.setIngredient(ingredient);
        dishIngredient.setDish(dish);
        dishIngredient.setWeight(100);

        List<DishIngredient> dishIngredientList = new ArrayList<>();
        dishIngredientList.add(dishIngredient);

        IngredientStorage ingredientStorage1 = IngredientsQuantityServiceImplTest.createIngredientStorage();
        ingredientStorage1.setIngredient(ingredient);
        ingredientStorage1.setQuantity(300);
        ingredientStorage1.setQuantityUsed(100);

        IngredientStorage ingredientStorage2 = IngredientsQuantityServiceImplTest.createIngredientStorage();
        ingredientStorage2.setIngredient(ingredient);
        ingredientStorage2.setQuantity(500);
        ingredientStorage2.setQuantityUsed(200);

        List<IngredientStorage> ingredientStorageList1 = new ArrayList<>();
        ingredientStorageList1.add(ingredientStorage1);
        ingredientStorageList1.add(ingredientStorage2);

        data.add(new Object[] {dishIngredientList, ingredientStorageList1, 800, 8});

        IngredientStorage ingredientStorage3 = IngredientsQuantityServiceImplTest.createIngredientStorage();
        ingredientStorage3.setIngredient(ingredient);
        ingredientStorage3.setQuantity(0);
        ingredientStorage3.setQuantityUsed(1000);

        IngredientStorage ingredientStorage4 = IngredientsQuantityServiceImplTest.createIngredientStorage();
        ingredientStorage4.setIngredient(ingredient);
        ingredientStorage4.setQuantity(200);
        ingredientStorage4.setQuantityUsed(0);

        List<IngredientStorage> ingredientStorageList2 = new ArrayList<>();
        ingredientStorageList2.add(ingredientStorage1);
        ingredientStorageList2.add(ingredientStorage2);
        ingredientStorageList2.add(ingredientStorage3);
        ingredientStorageList2.add(ingredientStorage4);

        data.add(new Object[] {dishIngredientList, ingredientStorageList2,1000, 10});

        return data;
    }

    @Test
    public void countIngredientsQuantityRest() {
        // Arrange - создаются объекты

        DishIngredientRepository dishIngredientRepository = Mockito.mock(DishIngredientRepository.class);
        Mockito
                .when(dishIngredientRepository.findAllByDish(dishIngredients.get(0).getDish()))
                .thenReturn(dishIngredients);

        IngredientStorageRepository ingredientStorageRepository = Mockito.mock(IngredientStorageRepository.class);
        Mockito
                .when(ingredientStorageRepository.findAllByIngredientOrderByReceiptDate(
                        dishIngredients.get(0).getIngredient()))
                .thenReturn(ingredientStorages);

        IngredientsQuantityServiceImpl ingredientsQuantityService =
                new IngredientsQuantityServiceImpl(dishIngredientRepository, ingredientStorageRepository);


        List<IngrAndQuantity> ingredientsExpected = new ArrayList<>();
        ingredientsExpected.add(new IngrAndQuantity(dishIngredients.get(0).getIngredient(), expectedIngredientCount));

        // Act - действие
        List<IngrAndQuantity> result = ingredientsQuantityService.countIngredientsQuantityRest(
                dishIngredients.get(0).getDish());

        // Asserts - прверки
        assertSame(ingredientsExpected.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            assertEquals(ingredientsExpected.get(i).getQuantity(), result.get(i).getQuantity());
        }
    }

    @Test
    public void countDishesCanCook() {
        // Arrange - создаются объекты

        DishIngredientRepository dishIngredientRepository = Mockito.mock(DishIngredientRepository.class);
        Mockito
                .when(dishIngredientRepository.findAllByDish(dishIngredients.get(0).getDish()))
                .thenReturn(dishIngredients);

        IngredientStorageRepository ingredientStorageRepository = Mockito.mock(IngredientStorageRepository.class);
        Mockito
                .when(ingredientStorageRepository.findAllByIngredientOrderByReceiptDate(
                        dishIngredients.get(0).getIngredient()))
                .thenReturn(ingredientStorages);

        IngredientsQuantityServiceImpl ingredientsQuantityService =
                new IngredientsQuantityServiceImpl(dishIngredientRepository, ingredientStorageRepository);

        // Act - действие
        Integer result = ingredientsQuantityService.countDishesCanCook(dishIngredients.get(0).getDish());

        // Asserts - прверки
        assertEquals(expectedDishCount, result);
    }

    @Test
    public void removeIngredientsFromStorage() {
    }

    @Test
    public void returnIngredientsToStorage() {
    }

    private static IngredientStorage createIngredientStorage() {
        IngredientStorage ingredientStorage = new IngredientStorage();
        ingredientStorage.setReceiptDate(new Date());

        return ingredientStorage;
    }
 }