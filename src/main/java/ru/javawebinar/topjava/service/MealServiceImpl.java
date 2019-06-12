package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServiceImpl implements MealService {
    private static final Logger log = getLogger(MealServiceImpl.class);

    private final MealRepository repository;


    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(Meal meal) {
        repository.add(meal);
    }

    @Override
    public void update(Meal meal) {
        repository.add(meal);
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public List<Meal> getAll() {
        return repository.getAll();
    }
}
