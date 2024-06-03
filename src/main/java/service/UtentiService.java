package service;


import dto.UtentiDTO;
import entities.Utenti;
import enums.Ruolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UtentiRepository;


@Service
public class UtentiService {

    @Autowired
    private UtentiRepository utentiRepository;



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



}
