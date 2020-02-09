package servlets;

import dto.AbilityDto;
import models.Hero;
import services.AbilityService;
import services.AbilityServiceImpl;
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
import java.util.List;

@MultipartConfig
@WebServlet("/heroedit/*")
public class HeroEdittingSerlvet extends HttpServlet {
    private HeroService heroService;
    private Long heroId;
    private AbilityService abilityService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        heroService = new HeroServiceImpl();
        abilityService = new AbilityServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String[] parts = path.split("/");
        this.heroId = Long.parseLong(parts[parts.length - 1]);
        Hero hero = heroService.get(heroId);
        req.setAttribute("hero", hero);
        List<AbilityDto> abilityDtos = abilityService.getAllWithFlaggedHeroAbilities(heroId);
        req.setAttribute("abilities", abilityDtos);
        req.getRequestDispatcher("/edit.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        heroService.update(req, this.heroId);
        resp.sendRedirect("../hero/" + heroId);
    }
}
