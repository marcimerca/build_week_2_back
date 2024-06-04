package project.epic_energy_back.dto.anagrafeDto;

import lombok.Data;
import project.epic_energy_back.enums.TipoSede;

@Data
public class IndirizzoAnagrafeDto {

    private Integer idProvincia;

    private Integer idAnagrafe;

    private Integer idCliente;

    private String via;

    private String civico;

    private String cap;

    private TipoSede tipoSede;


}
