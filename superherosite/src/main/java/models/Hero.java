package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hero implements Comparable<Hero> {
    private Long id;
    private String name;
    private String description;
    private Integer power;
    private Integer endurance;
    private Integer dexterity;
    private List<Ability> abilities = new LinkedList<>();
    private String photoPath;

    public void addAbility(Ability ability) {
        if (abilities == null) {
            abilities = new LinkedList<>();
        }
        this.abilities.add(ability);
    }

    @Override
    public int compareTo(Hero o) {
        return (int)(id - o.id);
    }
}
