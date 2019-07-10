package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.service.AbstractUserTest;

import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserTest {
    @Test
    public void getUserWithMealsTest() {
        MealTestData.assertMatch(service.getUserWithMeals(USER_ID).getMeals(), MealTestData.MEALS);
    }
}
