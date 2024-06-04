package project.epic_energy_back.entities;

import project.epic_energy_back.enums.STATO_FATTURA;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity

public class Fatture {
    @Id
    @GeneratedValue
    private int id;

    private LocalDate dataImporto;

    private Double importo;

    private String numero;

    private STATO_FATTURA statoFattura;




}
