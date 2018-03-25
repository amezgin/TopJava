package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final Meal USER_MEAL_1 = new Meal(START_SEQ + 2, LocalDateTime.of(2018, Month.MARCH, 1, 11, 0), "завтрак", 500);
    public static final Meal USER_MEAL_2 = new Meal(START_SEQ + 3, LocalDateTime.of(2018, Month.MARCH, 1, 13, 0), "обед", 1000);
    public static final Meal USER_MEAL_3 = new Meal(START_SEQ + 4, LocalDateTime.of(2018, Month.MARCH, 1, 18, 0), "ужин", 500);
    public static final Meal USER_MEAL_4 = new Meal(START_SEQ + 5, LocalDateTime.of(2018, Month.MARCH, 2, 11, 0), "завтрак", 500);
    public static final Meal USER_MEAL_5 = new Meal(START_SEQ + 6, LocalDateTime.of(2018, Month.MARCH, 2, 13, 0), "обед", 1000);
    public static final Meal USER_MEAL_6 = new Meal(START_SEQ + 7, LocalDateTime.of(2018, Month.MARCH, 2, 18, 0), "ужин", 600);
    public static final Meal ADMIN_MEAL_1 = new Meal(START_SEQ + 8, LocalDateTime.of(2018, Month.MARCH, 1, 11, 0), "завтрак", 500);
    public static final Meal ADMIN_MEAL_2 = new Meal(START_SEQ + 9, LocalDateTime.of(2018, Month.MARCH, 1, 13, 0), "обед", 1000);
    public static final Meal ADMIN_MEAL_3 = new Meal(START_SEQ + 10, LocalDateTime.of(2018, Month.MARCH, 1, 18, 0), "ужин", 500);
    public static final Meal ADMIN_MEAL_4 = new Meal(START_SEQ + 11, LocalDateTime.of(2018, Month.MARCH, 2, 11, 0), "завтрак", 500);
    public static final Meal ADMIN_MEAL_5 = new Meal(START_SEQ + 12, LocalDateTime.of(2018, Month.MARCH, 2, 13, 0), "обед", 1000);
    public static final Meal ADMIN_MEAL_6 = new Meal(START_SEQ + 13, LocalDateTime.of(2018, Month.MARCH, 2, 18, 0), "ужин", 500);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertThat(actual).containsSequence(expected);
    }
}
