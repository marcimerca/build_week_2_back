package project.epic_energy_back.service;


import project.epic_energy_back.dto.UtentiDTO;
import project.epic_energy_back.entities.Utenti;
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
        Utenti utenti = new Utenti();
        utenti.setUsername(utentiDTO.getUsername());
        utenti.setNome((utentiDTO.getNome()));
        utenti.setCognome((utentiDTO.getCognome()));
        utenti.setEmail((utentiDTO.getEmail()));

       //utenti.setPassword(utentiDTO.getPassword());

        utenti.setRuolo(Ruolo.USER);

        utenti.setPassword(passwordEncoder.encode(utentiDTO.getPassword()));

        utentiRepository.save(utenti);
        return "User with id=" + utenti.getId() + " correctly saved";


    }
    public Optional<Utenti> getUtenteByEmail(String email){
        return utentiRepository.findByEmail(email);
    }
   public Optional<Utenti> getUtenteById(int id){
        return utentiRepository.findById(id);
    }

}
