package ru.javawebinar.topjava.service.datajpa;

import org.hibernate.LazyInitializationException;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserTest;

import static org.junit.Assert.assertNull;
import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserTest {
    @Test
    public void getUserWithMealsTest() {
        User user = service.getUserWithMeals(USER_ID);
        UserTestData.assertMatch(user, USER);
        MealTestData.assertMatch(user.getMeals(), MEALS);
    }

    @Test
    public void getUserWithMealsNotFoundTest() {
        thrown.expect(LazyInitializationException.class);
        assertNull(service.get(USER_ID).getMeals());
    }
}
