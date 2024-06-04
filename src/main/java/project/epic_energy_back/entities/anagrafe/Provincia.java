package project.epic_energy_back.entities.anagrafe;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "province")
public class Provincia extends AnagrafeCenter {
    private String siglaProvincia;//AG
    private String regione;
    private String nomeProvincia;//Agrigento
}