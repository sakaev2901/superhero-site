package models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchCriteria {
    private String name;
    private String description;
    private Integer powerFrom;
    private Integer powerTo;
    private Integer enduranceFrom;
    private Integer enduranceTo;
    private Integer dexterityFrom;
    private Integer dexterityTo;
    private List<Ability> abilities;
}
