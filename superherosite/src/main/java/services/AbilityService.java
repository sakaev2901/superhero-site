package services;

import dto.AbilityDto;
import models.Ability;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AbilityService {
    List<Ability> getAll();
    List<AbilityDto> getAllWithFlaggedHeroAbilities(Long id);
    void saveAbility(HttpServletRequest request);
}
