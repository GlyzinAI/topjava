package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.Util;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepositoryImpl.ADMIN_ID;
import static ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepositoryImpl.USER_ID;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> userMealsRepository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, USER_ID));
        save(new Meal(LocalDateTime.of(2019, Month.MAY, 30, 10, 0), "Завтрак(Пользователь)", 500), USER_ID);
        save(new Meal(LocalDateTime.of(2019, Month.MAY, 30, 15, 0), "Обед(Пользователь)", 1200), USER_ID);
        save(new Meal(LocalDateTime.of(2019, Month.MAY, 30, 20, 0), "Ужин(Пользователь)", 300), USER_ID);
        save(new Meal(LocalDateTime.of(2019, Month.MAY, 31, 10, 0), "Завтрак(Пользователь)", 500), USER_ID);
        save(new Meal(LocalDateTime.of(2019, Month.MAY, 31, 14, 0), "Обед(Пользователь)", 1200), USER_ID);
        save(new Meal(LocalDateTime.of(2019, Month.MAY, 31, 20, 0), "Ужин(Пользователь)", 500), USER_ID);
        save(new Meal(LocalDateTime.of(2019, Month.JUNE, 18, 10, 0), "Завтрак(Админ)", 350), ADMIN_ID);
        save(new Meal(LocalDateTime.of(2019, Month.JUNE, 18, 14, 0), "Обед(Админ)", 1000), ADMIN_ID);
        save(new Meal(LocalDateTime.of(2019, Month.JUNE, 18, 20, 0), "Ужин(Админ)", 600), ADMIN_ID);
        save(new Meal(LocalDateTime.of(2019, Month.JUNE, 19, 10, 0), "Завтрак(Админ)", 400), ADMIN_ID);
        save(new Meal(LocalDateTime.of(2019, Month.JUNE, 19, 15, 0), "Обед(Админ)", 800), ADMIN_ID);
        save(new Meal(LocalDateTime.of(2019, Month.JUNE, 19, 20, 0), "Ужин(Админ)", 500), ADMIN_ID);

    }

    @Override
    public Meal save(Meal meal, int userId) {
        Map<Integer, Meal> meals = userMealsRepository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meals.put(meal.getId(), meal);
            return meal;
        }
        return meals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> meals = userMealsRepository.get(userId);
        return meals != null && meals.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> meals = userMealsRepository.get(userId);
        return meals != null ? meals.get(id) : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getFilter(userId, meal -> true);
    }

    @Override
    public List<Meal> gerFilterBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Map<Integer, Meal> meals = userMealsRepository.get(userId);
        return meals.isEmpty() ? Collections.emptyList() : meals.values().stream()
                .filter(meal -> Util.isBetween(meal.getDateTime(), startDate, endDate))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    private List<Meal> getFilter(int userId, Predicate<Meal> filter) {
        Map<Integer, Meal> meals = userMealsRepository.get(userId);
        return meals.isEmpty() ? Collections.emptyList() : meals.values().stream()
                .filter(filter)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

