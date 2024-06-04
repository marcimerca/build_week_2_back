package project.epic_energy_back.dto;


import lombok.Data;
import project.epic_energy_back.entities.Cliente;
import project.epic_energy_back.enums.STATO_FATTURA;

import java.time.LocalDate;

@Data
public class FatturaDTO {

    private LocalDate dataImporto;

    private Double importo;

    private String numero;

    private STATO_FATTURA statoFattura;

    private Cliente cliente;

}
