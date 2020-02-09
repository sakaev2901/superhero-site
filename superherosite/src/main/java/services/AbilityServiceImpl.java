package services;

import dto.AbilityDto;
import models.Ability;
import repositories.AbilityRepository;
import repositories.AbilityRepositoryImpl;
import services.AbilityService;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

public class AbilityServiceImpl implements AbilityService {

    private AbilityRepository abilityRepository;
    private List<AbilityDto> flaggedAbilities;
    private List<Ability> abilities;
    private List<Ability> heroAbilities;

    @Override
    public List<Ability> getAll() {
        abilityRepository = new AbilityRepositoryImpl();
        return abilityRepository.findAll();
    }

    @Override
    public List<AbilityDto> getAllWithFlaggedHeroAbilities(Long id) {
        flaggedAbilities = new LinkedList<>();
        abilityRepository = new AbilityRepositoryImpl();
        heroAbilities = abilityRepository.findHeroAbilities(id);
        abilities = abilityRepository.findAll();
        flagAbilities();
        return flaggedAbilities;
    }

    @Override
    public void saveAbility(HttpServletRequest request) {
        abilityRepository = new AbilityRepositoryImpl();
        Ability ability = Ability.builder()
                .name(request.getParameter("name"))
                .description(request.getParameter("description"))
                .build();
        abilityRepository.save(ability);
    }

    private void flagAbilities() {
        for (Ability ability:
             abilities) {
            flaggedAbilities.add(new AbilityDto(ability, isHeroAbilitiesContains(ability)));
        }
    }

    private short isHeroAbilitiesContains(Ability ability) {
        for (Ability heroAbility :heroAbilities) {
            if (heroAbility.equals(ability)) {
                return 1;
            }
        }
        return 0;
    }
}
