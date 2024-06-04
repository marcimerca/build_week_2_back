package project.epic_energy_back.dto;


import project.epic_energy_back.enums.Ruolo;
import lombok.Data;

@Data
public class UtentiDTO {
    private String username;

    private String email;

    private String password;

    private String nome;

    private String cognome;

    private String avatar;

   // private Ruolo ruolo;
}
