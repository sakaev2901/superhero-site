package servlets;

import models.Hero;
import services.HeroService;
import services.HeroServiceImpl;
import sun.rmi.runtime.Log;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
@WebServlet("/heroedit/*")
public class HeroEdittingSerlvet extends HttpServlet {
    private HeroService heroService;
    private Long heroId;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        heroService = new HeroServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String[] parts = path.split("/");
        this.heroId = Long.parseLong(parts[parts.length - 1]);
        Hero hero = heroService.get(heroId);
        req.setAttribute("hero", hero);
        req.getRequestDispatcher("/edit_hero.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        heroService.update(req, this.heroId);
    }
}
