package project.epic_energy_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.epic_energy_back.dto.anagrafeDto.IndirizzoAnagrafeDto;
import project.epic_energy_back.entities.Cliente;
import project.epic_energy_back.entities.Indirizzo;
import project.epic_energy_back.entities.anagrafe.AnagrafeCenter;
import project.epic_energy_back.exceptions.BadRequestException;
import project.epic_energy_back.repository.AnagrafeRepository;
import project.epic_energy_back.repository.IndirizzoRepository;

import java.util.List;
import java.util.Optional;
@Service
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;
    @Autowired
    private AnagrafeRepository anagrafeRepository;
    @Autowired
    private ClienteService clienteService;
public String saveIndirizzo(IndirizzoAnagrafeDto indirizzoAnagrafeDto) {

    if (indirizzoRepository.existsByClienteIdAndTipoSede(indirizzoAnagrafeDto.getIdCliente(), indirizzoAnagrafeDto.getTipoSede())) {
        throw new BadRequestException("Sede legale o operativa già esistente con ID: " + indirizzoAnagrafeDto.getIdCliente() + " e tipo sede: " + indirizzoAnagrafeDto.getTipoSede());
    }
    Indirizzo indirizzo = new Indirizzo();
    indirizzo.setTipoSede(indirizzoAnagrafeDto.getTipoSede());
    indirizzo.setVia(indirizzoAnagrafeDto.getVia());
    indirizzo.setCivico(indirizzoAnagrafeDto.getCivico());
    indirizzo.setCap(indirizzoAnagrafeDto.getCap());
    Optional<Cliente>optionalCliente=clienteService.getClienteById(indirizzoAnagrafeDto.getIdCliente());
    Optional<AnagrafeCenter> optionalAnagrafeCenter = anagrafeRepository.findById(indirizzoAnagrafeDto.getIdAnagrafe());
    if (!optionalCliente.isPresent()&&!optionalAnagrafeCenter.isPresent()) {
        throw new BadRequestException("Id del cliente/indirizo non trovato");
    }
        indirizzo.setCliente(optionalCliente.get());
        indirizzo.setLocalita(optionalAnagrafeCenter.get());
        indirizzoRepository.save(indirizzo);
        return "indirizzo salvato";

    }

    public Page<Indirizzo> getAllIndirizzi(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return indirizzoRepository.findAll(pageable);
    }
}
