package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final Meal FIRST_MEAL = new Meal(START_SEQ + 10, LocalDateTime.of(2019, Month.JUNE, 26, 20, 0, 0), "Ужин", 600);
    public static final Meal SECOND_MEAL = new Meal(START_SEQ + 9, LocalDateTime.of(2019, Month.JUNE, 26, 14, 0, 0), "Обед", 900);
    public static final Meal THIRD_MEAL = new Meal(START_SEQ + 8, LocalDateTime.of(2019, Month.JUNE, 26, 10, 0, 0), "Завтрак", 500);
    public static final Meal FORTH_MEAL = new Meal(START_SEQ + 7, LocalDateTime.of(2019, Month.JUNE, 25, 20, 0, 0), "Ужин", 600);
    public static final Meal FIFTH_MEAL = new Meal(START_SEQ + 6, LocalDateTime.of(2019, Month.JUNE, 25, 14, 0, 0), "Обед", 1100);
    public static final Meal SIXTH_MEAL = new Meal(START_SEQ + 5, LocalDateTime.of(2019, Month.JUNE, 25, 10, 0, 0), "Завтрак", 500);
    public static final Meal FIRST_ADMIN_MEAL = new Meal(START_SEQ + 4, LocalDateTime.of(2019, Month.JUNE, 24, 20, 0, 0), "Ужин (Админ)", 500);
    public static final Meal SECOND_ADMIN_MEAL = new Meal(START_SEQ + 3, LocalDateTime.of(2019, Month.JUNE, 24, 14, 0, 0), "Обед (Админ)", 1000);
    public static final Meal THIRD_ADMIN_MEAL = new Meal(START_SEQ + 2, LocalDateTime.of(2019, Month.JUNE, 24, 10, 0, 0), "Завтрак (Админ)", 500);


    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    private static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}
