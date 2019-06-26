package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(FIRST_ADMIN_MEAL.getId(), ADMIN_ID);
        assertMatch(meal, FIRST_ADMIN_MEAL);
    }

    @Test(expected = NotFoundException.class)
    public void getAnotherUserMeal() {
        service.get(SIXTH_MEAL.getId(), ADMIN_ID);
    }

    @Test
    public void delete() {
        service.delete(THIRD_ADMIN_MEAL.getId(), ADMIN_ID);
        assertMatch(service.getAll(ADMIN_ID), FIRST_ADMIN_MEAL, SECOND_ADMIN_MEAL);
    }

    @Test(expected = NotFoundException.class)
    public void deleteAnotherUserMeal() {
        service.delete(FIFTH_MEAL.getId(), ADMIN_ID);
    }

    @Test
    public void getBetweenDateTimes() {
        LocalDateTime start = LocalDateTime.of(FIRST_MEAL.getDate(), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(FIRST_MEAL.getDate(), LocalTime.MAX);
        List<Meal> meals = service.getBetweenDateTimes(start, end, USER_ID);
        assertMatch(meals, FIRST_MEAL, SECOND_MEAL, THIRD_MEAL);
    }

    @Test
    public void getAll() {
        List<Meal> mealList = service.getAll(USER_ID);
        assertMatch(mealList, FIRST_MEAL, SECOND_MEAL, THIRD_MEAL, FORTH_MEAL, FIFTH_MEAL, SIXTH_MEAL);
    }

    @Test
    public void update() {
        Meal updated = new Meal(FIRST_MEAL);
        updated.setDescription("UpdatedDesc");
        updated.setCalories(600);
        service.update(updated, USER_ID);
        assertMatch(service.get(FIRST_MEAL.getId(), USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateAnotherUserMeal() {
        service.update(FIRST_ADMIN_MEAL, USER_ID);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(LocalDateTime.now(), "desc", 1000);
        Meal created = service.create(newMeal, ADMIN_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(ADMIN_ID), newMeal, FIRST_ADMIN_MEAL, SECOND_ADMIN_MEAL, THIRD_ADMIN_MEAL);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateDateCreate() {
        service.create(new Meal(FIRST_MEAL.getDateTime(), "Ужин", 600), USER_ID);
    }
}