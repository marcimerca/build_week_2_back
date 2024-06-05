package project.epic_energy_back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import project.epic_energy_back.enums.STATO_FATTURA;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Fattura {
    @Id
    @GeneratedValue
    private int id;

    private LocalDate dataImporto;

    private Double importo;

    private String numero;

    @Enumerated(EnumType.STRING)
    private STATO_FATTURA statoFattura;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;




}
