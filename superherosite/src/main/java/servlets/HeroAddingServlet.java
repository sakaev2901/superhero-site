package servlets;

import models.Ability;
import models.Hero;
import services.AbilityService;
import services.AbilityServiceImpl;
import services.HeroService;
import services.HeroServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@MultipartConfig
@WebServlet("/addhero")
public class HeroAddingServlet extends HttpServlet {
    private HeroService heroService;
    private AbilityService abilityService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.heroService = new HeroServiceImpl();
        abilityService = new AbilityServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ability> abilities = abilityService.getAll();
        req.setAttribute("abilities", abilities);
        req.getRequestDispatcher("/add_form.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        heroService.add(req);
        resp.sendRedirect("main");
    }
}
