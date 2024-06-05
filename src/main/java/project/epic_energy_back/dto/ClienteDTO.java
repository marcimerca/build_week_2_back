package project.epic_energy_back.dto;


import jakarta.validation.constraints.*;
import lombok.Data;
import project.epic_energy_back.enums.TipoSocieta;
import java.time.LocalDate;


@Data
public class ClienteDTO {
    @NotBlank(message = "ragioneSociale non può essere vuota")
    @Size(max = 50, message = "La ragione sociale non può superare i 100 caratteri")
    private String ragioneSociale;
    @NotBlank(message = "La partita IVA non può essere vuota")
    @Pattern(regexp = "\\d{11}", message = "La partita IVA deve contenere esattamente 11 cifre")
    private String partitaIva;
    @Email
    @NotBlank(message = "L'email non può essere null, vuota, o composta da soli spazi")
    private String email;
    @NotNull(message = "dataInserimento non può essere null")
    private LocalDate dataInserimento;
    @NotNull(message = "dataUltimoContatto non può essere null")
    private LocalDate dataUltimoContatto;
    @NotNull(message = "fatturatoAnnuale non può essere null")
    private Double fatturatoAnnuale;
    @NotBlank(message = "La pec non può essere vuota")
    @Email(message = "La pec deve essere valida")
    private String pec;

    @NotBlank(message = "telefono non può essere null, vuota, o composta da soli spazi")
    @Pattern(regexp = "\\+?\\d+", message = "Il numero di telefono deve essere valido")
    private String telefono;

    @Email
    @NotBlank(message = "emailContatto non può essere null, vuota, o composta da soli spazi")
    private String emailContatto;
    @NotBlank(message = "nomeContatto non può essere null, vuoto, o composta da soli spazi")
    private String nomeContatto;
    @NotBlank(message = "cognomeContatto non può essere null, vuoto, o composta da soli spazi")
    private String cognomeContatto;
    @NotBlank(message = "telefonoContatto non può essere null, vuoto, o composta da soli spazi")
    private String telefonoContatto;

    private String logoAziendale;

    @NotNull(message = "tipoSocieta non può essere null e può assumere come valori solo:  PA, SPA, SRL, SAS")
    private TipoSocieta tipoSocieta;

}
