package servlets;

import models.Hero;
import services.HeroService;
import services.HeroServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hero/*")
public class HeroPageServlet extends HttpServlet {

    private HeroService heroService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        heroService = new HeroServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        Long id = Long.parseLong(path.substring(1));
        Hero hero = heroService.get(id);
        req.setAttribute("hero", hero);
        req.getRequestDispatcher("/hero.ftl").forward(req, resp);
    }
}
