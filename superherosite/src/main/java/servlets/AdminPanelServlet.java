package servlets;

import services.AbilityService;
import services.AbilityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminPanelServlet extends HttpServlet {

    private AbilityService abilityService;

    @Override
    public void init() throws ServletException {
        super.init();
        abilityService = new AbilityServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("abilities", abilityService.getAll());
        req.getRequestDispatcher("/admin.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        abilityService.saveAbility(req);
        doGet(req, resp);
    }
}
