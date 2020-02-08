package repositories;

import models.Ability;

public interface AbilityRepository  extends  CrudRepository<Ability, Long>{
    Ability findByName(String name);
}
