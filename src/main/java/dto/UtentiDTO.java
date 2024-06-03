package dto;


import enums.Ruolo;
import lombok.Data;

@Data
public class UtentiDTO {
    private String username;

    private String email;

    private String password;

    private String nome;

    private String cognome;

    private String avatar;

    private Ruolo ruolo;
}
