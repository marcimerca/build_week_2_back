package project.epic_energy_back.dto;


import lombok.Data;
import project.epic_energy_back.enums.TipoSocieta;
import java.time.LocalDate;


@Data
public class ClienteDTO {

    private String ragioneSociale;
    private String partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private Double fatturatoAnnuale;
    private String pec;
    private String telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;
    private String logoAziendale;

    private TipoSocieta tipoSocieta;

}
