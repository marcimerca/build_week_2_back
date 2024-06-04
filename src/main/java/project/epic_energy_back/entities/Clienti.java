package project.epic_energy_back.entities;


import project.epic_energy_back.enums.TipoSocieta;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Clienti {

    @Id
    @GeneratedValue
    private int id;

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
