package servlets;

import models.Ability;
import models.Hero;
import services.AbilityService;
import services.AbilityServiceImpl;
import services.HeroService;
import services.HeroServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@WebServlet("/main")
public class MainPageServlet extends HttpServlet {
    private HeroService heroService;
    private AbilityService abilityService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        heroService = new HeroServiceImpl();
        abilityService = new AbilityServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Hero> heroes = null;
        if (req.getParameter("powerFrom") != null) {
            heroes = heroService.getWithCriteria(req);
        } else {
            heroes = heroService.getAll();
        }
        Collections.sort(heroes);
        req.setAttribute("heroes", heroes);
        req.setAttribute("abilities", abilityService.getAll());
        req.getRequestDispatcher("/main2.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Hero> heroes = heroService.getWithCriteria(req);
        System.out.println(9);
        req.setAttribute("heroes", heroes);
        req.getRequestDispatcher("main2.ftl").forward(req, resp);
    }
}
