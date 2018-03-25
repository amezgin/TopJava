package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_1;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_2;
import static ru.javawebinar.topjava.MealTestData.USER_MEAL_1;
import static ru.javawebinar.topjava.MealTestData.USER_MEAL_2;
import static ru.javawebinar.topjava.MealTestData.USER_MEAL_3;
import static ru.javawebinar.topjava.MealTestData.USER_MEAL_4;
import static ru.javawebinar.topjava.MealTestData.USER_MEAL_5;
import static ru.javawebinar.topjava.MealTestData.USER_MEAL_6;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
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
    public void testGet() {
        Meal actual = service.get(100008, ADMIN_ID);
        assertMatch(actual, ADMIN_MEAL_1);
    }

    @Test
    public void testDelete() {
        service.delete(100002, USER_ID);
        List<Meal> actual = service.getAll(USER_ID);
        assertMatch(actual, USER_MEAL_6, USER_MEAL_5, USER_MEAL_4, USER_MEAL_3, USER_MEAL_2);
    }

    @Test
    public void testGetBetweenDateTimes() {
        List<Meal> actual = service.getBetweenDateTimes(LocalDateTime.of(2018, Month.MARCH, 1, 11, 0),
                LocalDateTime.of(2018, Month.MARCH, 1, 13, 0), ADMIN_ID);
        assertMatch(actual, ADMIN_MEAL_2, ADMIN_MEAL_1);
    }

    @Test
    public void testGetAll() {
        List<Meal> actual = service.getAll(USER_ID);
        assertMatch(actual, USER_MEAL_6, USER_MEAL_5, USER_MEAL_4, USER_MEAL_3, USER_MEAL_2, USER_MEAL_1);
    }

    @Test
    public void testUpdate() {
        Meal updated = new Meal(USER_MEAL_1);
        updated.setCalories(1000);
        service.update(updated, USER_ID);
        assertMatch(service.get(100002, USER_ID), updated);
    }

    @Test
    public void testCreate() {
        Meal newMeal = new Meal(null, LocalDateTime.of(2018, Month.MARCH, 3, 11, 0), "завтрак", 500);
        Meal created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(USER_ID), newMeal, USER_MEAL_6, USER_MEAL_5, USER_MEAL_4, USER_MEAL_3, USER_MEAL_2, USER_MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void deleteSomeoneMeal() {
        service.delete(100002, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getSomeoneMeal() {
        service.get(100002, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void updateSomeoneMeal() {
        Meal updated = new Meal(USER_MEAL_1);
        updated.setCalories(1000);
        service.update(updated, ADMIN_ID);
    }
}