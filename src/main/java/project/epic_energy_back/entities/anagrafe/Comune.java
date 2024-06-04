package project.epic_energy_back.entities.anagrafe;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comuni")
public class Comune extends AnagrafeCenter {

    private long pfProvincia;
    private long pfComune;
    private String nomeComune;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;
}