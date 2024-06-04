package project.epic_energy_back.entity.anagrafe;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name = "province")
public class Provincia extends AnagrafeCenter {
    private String siglaProvincia;//AG
    private String regione;
    private String nomeProvincia;//Agrigento
}