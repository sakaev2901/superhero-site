package services;

import models.Ability;
import models.Hero;
import models.SearchCriteria;
import repositories.AbilityRepository;
import repositories.AbilityRepositoryImpl;
import repositories.HeroRepository;
import repositories.HeroRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@MultipartConfig
public class HeroServiceImpl implements HeroService<HttpServletRequest> {

    private HeroRepository heroRepository;
    private AbilityRepository abilityRepository;
    private HttpServletRequest request;
    private Hero hero;

    public HeroServiceImpl() {
        heroRepository = new HeroRepositoryImpl();
        abilityRepository = new AbilityRepositoryImpl();
    }

    public void add(HttpServletRequest request) {
        this.request = request;
        heroInitialization();
        saveImage();
        heroRepository.save(hero);
    }

    private void heroInitialization() {

        String description = this.request.getParameter("description");
        String name = this.request.getParameter("name");
        System.out.println(this.request.getParameter("power"));
        Integer power = Integer.parseInt(this.request.getParameter("power"));
        Integer endurance = Integer.parseInt(this.request.getParameter("endurance"));
        Integer dexterity = Integer.parseInt(this.request.getParameter("dexterity"));
        String[] abilitiesArray = this.request.getParameterValues("abilities");
        LinkedList<Ability> abilities = new LinkedList<>();
        if (abilitiesArray != null)  {
            for (String abilityName :
                    abilitiesArray) {
                if (!abilityName.equals("Нет способностей")) {
                    abilities.add(abilityRepository.findByName(abilityName));
                }
            }
        }
        this.hero = Hero.builder()
                .name(name)
                .description(description)
                .power(power)
                .endurance(endurance)
                .dexterity(dexterity)
                .abilities(abilities)
                .build();
    }

    private void saveImage() {
        try {
            Part photo = request.getPart("photo");
//            String localdir = System.getProperty("catalina.home") + File.separator + "uploads";
            String[] a = request.getServletContext().getRealPath("/").split("\\\\");
            String b = "";
            for (int i = 0; i < a.length - 2; i++) {
                b += a[i] + "\\";
            }
            b += "src\\main\\webapp\\resources\\";
            System.out.println(b);
            String localdir = b + "uploads";
            File dir = new File(localdir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String[] filename_data = photo.getSubmittedFileName().split("\\.");
            String filename = Math.random() + "." + filename_data[filename_data.length - 1];
            if (!filename.equals("")) {
                String fullpath = localdir + File.separator + filename;
                photo.write(fullpath);
                hero.setPhotoPath(filename);
            }
        } catch (IOException | ServletException e) {
            throw new IllegalStateException(e);
        }


    }


    public void delete(Long id) {
        heroRepository.delete(id);
    }

    public void update(HttpServletRequest request, Long id) {
        this.request = request;
        heroInitialization();
        hero.setId(id);
        saveImage();
        heroRepository.update(hero);
    }

    @Override
    public Hero get(Long id) {
        return heroRepository.findById(id);
    }


    @Override
    public List<Hero> getAll() {
        return heroRepository.findAll();
    }

    @Override
    public List<Hero> getByCriteria() {
        return null;
    }

    @Override
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public List<Hero> getWithCriteria(HttpServletRequest request) {
        this.request = request;
        String description = this.request.getParameter("description");
        String name = this.request.getParameter("name");
        System.out.println(this.request.getParameter("power"));
        Integer powerFrom = Integer.parseInt(Optional.ofNullable(this.request.getParameter("powerFrom")).orElse("1"));
        Integer powerTo = Integer.parseInt(Optional.ofNullable(this.request.getParameter("powerTo")).orElse("100"));
        Integer enduranceFrom = Integer.parseInt(Optional.ofNullable(this.request.getParameter("enduranceFrom")).orElse("1"));
        Integer enduranceTo = Integer.parseInt(Optional.ofNullable(this.request.getParameter("enduranceTo")).orElse("100"));
        Integer dexterityFrom = Integer.parseInt(Optional.ofNullable(this.request.getParameter("dexterityFrom")).orElse("1"));
        Integer dexterityTo = Integer.parseInt(Optional.ofNullable(this.request.getParameter("dexterityTo")).orElse("100"));
        String[] abilitiesArray = this.request.getParameterValues("abilities");
        LinkedList<Ability> abilities = new LinkedList<>();
        if (abilitiesArray != null)  {
            for (String abilityName :
                    abilitiesArray) {
                abilities.add(abilityRepository.findByName(abilityName));
            }
        }
        SearchCriteria searchCriteria = SearchCriteria.builder()
                .name(name)
                .description(description)
                .powerFrom(powerFrom)
                .powerTo(powerTo)
                .enduranceFrom(enduranceFrom)
                .enduranceTo(enduranceTo)
                .dexterityFrom(dexterityFrom)
                .dexterityTo(dexterityTo)
                .build();
        return heroRepository.findByCriteria(searchCriteria);
    }


}
