package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Send the list meals with exceed on meals.jsp");
        request.setCharacterEncoding("UTF-8");
        List<MealWithExceed> list = MealsUtil.getAllMealsWithExceeded(MealsUtil.meals, MealsUtil.DEFAULT_CALORIES_PER_DAY);
        request.setAttribute("mealList", list);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
