package ru.javawebinar.topjava.service.datajpa;

import org.hibernate.LazyInitializationException;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.AbstractMealTest;

import static org.junit.Assert.assertNull;
import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles(DATAJPA)
public class DataJpaMealServiceTest extends AbstractMealTest {
    @Test
    public void getMealWithUserTest() {
        Meal meal = service.getMealWithUser(MEAL1_ID, USER_ID);
        Meal expected = MEAL1;
        expected.setUser(USER);
        UserTestData.assertMatch(meal.getUser(), expected.getUser());
        MealTestData.assertMatch(meal, expected);
    }

    @Test
    public void getMealWithUserNotFoundTest() {
        thrown.expect(LazyInitializationException.class);
        assertNull(service.get(MEAL1_ID, USER_ID).getUser());
    }
}
