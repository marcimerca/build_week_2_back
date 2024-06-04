package project.epic_energy_back.service;


import project.epic_energy_back.dto.UtentiDTO;
import project.epic_energy_back.entities.Utente;
import project.epic_energy_back.enums.Ruolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.epic_energy_back.repository.UtentiRepository;

import java.util.Optional;


@Service
public class UtentiService {

    @Autowired
    private UtentiRepository utentiRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public String saveUser(UtentiDTO utentiDTO) {
        Utente utente = new Utente();
        utente.setUsername(utentiDTO.getUsername());
        utente.setNome((utentiDTO.getNome()));
        utente.setCognome((utentiDTO.getCognome()));
        utente.setEmail((utentiDTO.getEmail()));

       //utenti.setPassword(utentiDTO.getPassword());

        utente.setRuolo(Ruolo.USER);

        utente.setPassword(passwordEncoder.encode(utentiDTO.getPassword()));

        utentiRepository.save(utente);
        return "User with id=" + utente.getId() + " correctly saved";


    }
    public Optional<Utente> getUtenteByEmail(String email){
        return utentiRepository.findByEmail(email);
    }
   public Optional<Utente> getUtenteById(int id){
        return utentiRepository.findById(id);
    }

}
