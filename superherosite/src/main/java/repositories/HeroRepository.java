package repositories;

import models.Hero;
import models.SearchCriteria;

import java.util.List;

public interface HeroRepository extends CrudRepository<Hero, Long> {
    List<Hero> findByCriteria(SearchCriteria criteria);
}
