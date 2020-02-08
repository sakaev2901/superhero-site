package services;

import models.Hero;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface HeroService<Request> {
    void add(Request request);
    void delete(Long id);
    void update(Request request, Long Id);
    Hero get(Long id);
    List<Hero> getAll();
    List<Hero> getByCriteria();
    void setRequest(Request request);
    List<Hero> getWithCriteria(Request request);
}
