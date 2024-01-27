package ru.geekbrains.junior.lesson1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Корзина
 *
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     *
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market) {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    //endregion

    /**
     * Балансировка корзины
     */
    public void cardBalancing() {
        try {
            Optional<T> proteins = foodstuffs.stream()
                    .filter(Food::getProteins)
                    .findFirst();
            Optional<T> fats = foodstuffs.stream()
                    .filter(Food::getFats)
                    .findFirst();
            Optional<T> carbs = foodstuffs.stream()
                    .filter(Food::getCarbohydrates)
                    .findFirst();
            if (proteins.isPresent() && fats.isPresent() && carbs.isPresent()) {
                System.out.println("Корзина уже сбалансирована по БЖУ.");
                return;
            }
            if (proteins.isEmpty()) {
                proteins = market.getThings(clazz).stream()
                        .filter(Food::getProteins)
                        .findFirst();
                proteins.ifPresent(foodstuffs::add);
            }
            if (fats.isEmpty()) {
                fats = market.getThings(clazz).stream()
                        .filter(Food::getFats)
                        .findFirst();
                fats.ifPresent(foodstuffs::add);
            }
            if (carbs.isEmpty()) {
                carbs = market.getThings(clazz).stream()
                        .filter(Food::getCarbohydrates)
                        .findFirst();
                carbs.ifPresent(foodstuffs::add);
            }
            if (proteins.isPresent() && fats.isPresent() && carbs.isPresent()) {
                System.out.println("Корзина сбалансирована по БЖУ.");
            } else {
                System.out.println("Невозможно сбалансировать корзину по БЖУ.");
            }
        } catch (NullPointerException n) {
            System.out.println("Ошибка балансировки корзины.");
        }
    }

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    /**
     * Распечатать список продуктов в корзине
     */
    public void printFoodstuffs() {
        /*int index = 1;
        for (var food : foodstuffs)
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, food.getName(), food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет", food.getCarbohydrates() ? "Да" : "Нет");
         */
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));

    }

}
