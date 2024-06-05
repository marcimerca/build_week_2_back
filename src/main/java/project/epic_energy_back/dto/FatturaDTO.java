package project.epic_energy_back.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import project.epic_energy_back.entities.Cliente;
import project.epic_energy_back.enums.STATO_FATTURA;

import java.time.LocalDate;

@Data
public class FatturaDTO {

    @NotNull(message = "dataImporto non può essere nulla")
    private LocalDate dataImporto;

    @NotNull(message = "importo non può essere nullo")
    @Positive(message = "importo deve essere positivo")
    private Double importo;

    @NotBlank(message = "Il numero non può essere vuoto")
    @Size(max = 50, message = "Il numero non può superare i 50 caratteri")
    private String numero;

    @NotNull(message = "Lo statoFattura non può essere nullo")
    private STATO_FATTURA statoFattura;

    @NotNull(message = "idCliente non può essere nullo")
    private int idCliente;
}


