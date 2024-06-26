package project.epic_energy_back.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import project.epic_energy_back.enums.TipoSocieta;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
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


    @Enumerated(EnumType.STRING)
    private TipoSocieta tipoSocieta;
   /* @JsonIgnore*/
    @OneToMany(mappedBy = "cliente")
    private List<Indirizzo> indirizzi = new ArrayList<>();

   @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Fattura> fatture = new ArrayList<>();


}
