package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {

    void add(Meal meal);

    void update(Meal meal);

    Meal get(int id);

    void delete(int id);

    List<Meal> getAll();
}
