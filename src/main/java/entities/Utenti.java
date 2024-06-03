package entities;

import enums.Ruolo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Utenti {

    @Id
    @GeneratedValue
    private int id;

    private String username;

    private String email;

    private String password;

    private String nome;

    private String cognome;

    private String avatar;

    private Ruolo ruolo;


}
