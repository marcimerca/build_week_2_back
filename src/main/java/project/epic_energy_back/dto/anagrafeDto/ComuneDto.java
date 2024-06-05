package project.epic_energy_back.dto.anagrafeDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ComuneDto {
    @NotNull(message = "codiceProvincia cannot be null")
    private Long codiceProvincia;

    @NotNull(message = "progressivoComune cannot be null")
    private Long progressivoComune;

    @NotBlank(message = "denominazioneItaliano cannot be blank")
    private String denominazioneItaliano;

    @NotBlank(message = "provincia cannot be blank")
    private String provincia;

    @NotBlank(message = "Regione cannot be blank")
    private String Regione;
}