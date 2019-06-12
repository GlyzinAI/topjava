package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepositoryImpl;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final MealService service = new MealServiceImpl(new MealRepositoryImpl());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        LocalDateTime date = LocalDateTime.parse(request.getParameter("date"));
        String desc = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        if (id == null) {
            service.add(new Meal(date, desc, calories));
        } else {
            service.update(new Meal(Integer.parseInt(id), date, desc, calories));
        }
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            log.info("getAll");
            request.setAttribute("meals", MealsUtil.getWithExcess(service.getAll(), 2000));
            request.getRequestDispatcher("meals.jsp").forward(request, response);
            return;
        }

        switch (action) {
            case "delete":
                int id = getId(request);
                log.info("delete ", id);
                service.delete(id);
                response.sendRedirect("meals");
                return;
            case "add":
                log.info("add");
                request.setAttribute("date", LocalDateTime.now());
                request.getRequestDispatcher("add.jsp").forward(request, response);
                break;
            case "edit":
                int id1 = getId(request);
                log.info("edit", id1);
                request.setAttribute("meal", service.get(id1));
                request.getRequestDispatcher("edit.jsp").forward(request, response);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
    }

    private int getId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }
}
