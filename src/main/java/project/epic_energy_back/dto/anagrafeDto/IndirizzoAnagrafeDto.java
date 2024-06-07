package project.epic_energy_back.dto.anagrafeDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import project.epic_energy_back.enums.TipoSede;

@Data
public class IndirizzoAnagrafeDto {

/*
    @NotNull(message = "idProvincia cannot be null")
    private Integer idProvincia;
*/

    @NotNull(message = "idAnagrafe cannot be null")
    private Integer idAnagrafe;

    @NotNull(message = "idCliente cannot be null")
    private Integer idCliente;

    @NotBlank(message = "via cannot be blank")
    private String via;

    @NotBlank(message = "civico cannot be blank")
    private String civico;

    @NotBlank(message = "cap cannot be blank")
    private String cap;

    @NotNull(message = "tipoSede cannot be null")
    private TipoSede tipoSede;


}
