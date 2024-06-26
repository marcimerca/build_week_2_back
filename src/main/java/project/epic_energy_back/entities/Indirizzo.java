package project.epic_energy_back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import project.epic_energy_back.entities.anagrafe.AnagrafeCenter;
import project.epic_energy_back.enums.TipoSede;

@Data
@Entity
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private TipoSede tipoSede;

    private String via;
    private String civico;
    private String cap;

    @ManyToOne
    @JoinColumn(name ="localita_id")
    private AnagrafeCenter localita;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name ="clienti_id")
    private Cliente cliente;

}
