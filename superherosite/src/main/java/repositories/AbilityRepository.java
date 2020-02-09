package repositories;

import dto.AbilityDto;
import models.Ability;
import repositories.CrudRepository;

import java.util.List;

public interface AbilityRepository  extends CrudRepository<Ability, Long> {
    Ability findByName(String name);
    List<Ability> findHeroAbilities(Long id);
}
