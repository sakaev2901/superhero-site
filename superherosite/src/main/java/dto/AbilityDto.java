package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import models.Ability;

@Data
@Builder
@AllArgsConstructor
public class AbilityDto {
    private Ability ability;
    private short heroAbilityFlag;
}
