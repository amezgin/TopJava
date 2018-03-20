package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MealRestController {

    private static final Logger log = getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public Meal get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get meal {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete meal {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public List<MealWithExceed> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll for User {}", userId);
        return MealsUtil.getWithExceeded(service.getAll(userId), AuthorizedUser.getCaloriesPerDay());
    }

    public void update(Meal meal, int id) {
        meal.setId(id);
        int userId = AuthorizedUser.id();
        log.info("update {} for User {}", meal, userId);
        service.update(meal, userId);
    }

    public Meal create(Meal meal) {
        meal.setId(null);
        int userId = AuthorizedUser.id();
        log.info("create {} for User {}", meal, userId);
        return service.create(meal, userId);
    }
}