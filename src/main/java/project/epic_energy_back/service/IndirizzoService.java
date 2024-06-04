package project.epic_energy_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.epic_energy_back.dto.anagrafeDto.IndirizzoAnagrafeDto;
import project.epic_energy_back.entities.Indirizzo;
import project.epic_energy_back.entities.anagrafe.AnagrafeCenter;
import project.epic_energy_back.repository.AnagrafeRepository;
import project.epic_energy_back.repository.IndirizzoRepository;

import java.util.Optional;
@Service
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;
    @Autowired
    private AnagrafeRepository anagrafeRepository;
public String saveIndirizzo(IndirizzoAnagrafeDto indirizzoAnagrafeDto) {

    Indirizzo indirizzo = new Indirizzo();
    indirizzo.setTipoSede(indirizzoAnagrafeDto.getTipoSede());
    indirizzo.setVia(indirizzoAnagrafeDto.getVia());
    indirizzo.setCivico(indirizzoAnagrafeDto.getCivico());
    indirizzo.setCap(indirizzoAnagrafeDto.getCap());
    Optional<AnagrafeCenter> optionalAnagrafeCenter = anagrafeRepository.findById(indirizzoAnagrafeDto.getIdAnagrafe());
    if (optionalAnagrafeCenter.isPresent()) {
        indirizzo.setLocalita(optionalAnagrafeCenter.get());
        indirizzoRepository.save(indirizzo);
        return "indirizzo salvato";
    } else {
        throw new RuntimeException("Id dell'indirizzo non trovato");
    }
}
// manca connessione con il cliente
}
