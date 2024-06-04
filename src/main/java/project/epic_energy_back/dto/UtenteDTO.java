package project.epic_energy_back.dto;


import lombok.Data;

@Data
public class UtenteDTO {
    private String username;

    private String email;

    private String password;

    private String nome;

    private String cognome;

    private String avatar;

   // private Ruolo ruolo;
}
