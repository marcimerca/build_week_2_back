package project.epic_energy_back.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UtenteDTO {
    @NotBlank(message = "username non può essere null, vuoto, o composta da soli spazi")
    private String username;

    @Email
    @NotBlank(message = "email non può essere null, vuota, o composta da soli spazi")
    private String email;

    @NotBlank(message = "password non può essere null, vuota, o composta da soli spazi")
    private String password;

    @NotBlank(message = "nome non può essere null, vuota, o composta da soli spazi")
    private String nome;

    @NotBlank(message = "cognome non può essere null, vuota, o composta da soli spazi")
    private String cognome;

    private String avatar;


}
