package project.epic_energy_back.entity.anagrafe;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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