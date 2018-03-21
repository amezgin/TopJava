package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, Integer userId) {
        meal.setUserId(userId);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, Integer userId) {
        Meal meal = null;
        if (this.get(id, userId) != null) {
            meal = repository.remove(id);
        }
        return meal != null;
    }

    @Override
    public Meal get(int id, Integer userId) {
        Meal meal = null;
        Meal mealFromRepository = repository.get(id);
        if (mealFromRepository != null && mealFromRepository.getUserId() == userId) {
            meal = mealFromRepository;
        }
        return meal;
    }

    @Override
    public List<Meal> getAll(Integer userId) {
        List<Meal> result = new LinkedList<>();
        for (Meal meal : repository.values()) {
            if (meal.getUserId() == userId) {
                result.add(meal);
            }
        }
        Collections.sort(result, dateTime);
        return result;
    }

    @Override
    public List<Meal> getBetweenDates(LocalDate startDate, LocalDate endDate, Integer userId) {
        List<Meal> result = new LinkedList<>();
        for (Meal meal : repository.values()) {
            if (meal.getUserId() == userId && DateTimeUtil.isBetween(meal.getDate(), startDate, endDate)) {
                result.add(meal);
            }
        }
        Collections.sort(result, dateTime);
        return result;
    }

    private Comparator<Meal> dateTime = new Comparator<Meal>() {
        @Override
        public int compare(Meal o1, Meal o2) {
            return o2.getDateTime().compareTo(o1.getDateTime());
        }
    };
}