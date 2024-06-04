package project.epic_energy_back.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.epic_energy_back.controller.FatturaController;
import project.epic_energy_back.dto.FatturaDTO;
import project.epic_energy_back.entities.Cliente;
import project.epic_energy_back.entities.Fattura;
import project.epic_energy_back.exceptions.BadRequestException;
import project.epic_energy_back.exceptions.NotFoundException;
import project.epic_energy_back.repository.FatturaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FatturaService {

    @Autowired
    private FatturaRepository fatturaRepository;


    public String saveFattura(FatturaDTO fatturaDTO){

        Fattura fattura = new Fattura();

        fattura.setDataImporto(fatturaDTO.getDataImporto());
        fattura.setImporto(fatturaDTO.getImporto());
        fattura.setNumero(fatturaDTO.getNumero());
        fattura.setStatoFattura(fatturaDTO.getStatoFattura());
        fattura.setCliente(fatturaDTO.getCliente());

        fatturaRepository.save(fattura);
        return "Fattura with id=" + fattura.getId() + " correctly saved";
    }

    public List<Fattura> getAllFatture(){

        return fatturaRepository.findAll();

    }

    public Optional<Fattura> getFatturaById(int id){
        return fatturaRepository.findById(id);
    }


    public Fattura updateFattura( int id, FatturaDTO fatturaDTO) {
        Optional<Fattura> fatturaOptional = fatturaRepository.findById(id);
        if(fatturaOptional.isPresent()){
            Fattura fattura = fatturaOptional.get();
            fattura.setDataImporto(fatturaDTO.getDataImporto());
            fattura.setImporto(fatturaDTO.getImporto());
            fattura.setNumero(fatturaDTO.getNumero());
            fattura.setStatoFattura(fatturaDTO.getStatoFattura());
            fattura.setCliente(fatturaDTO.getCliente());
            fatturaRepository.save(fattura);
            return fattura;
        } else {
            throw new NotFoundException("Fattura con id " + id + " non trovata.");
        }
    }


    public String deleteFattura(int id){
        Optional<Fattura> fatturaOptional = fatturaRepository.findById(id);
        if(fatturaOptional.isPresent()){
            fatturaRepository.delete(fatturaOptional.get());
            return "Fattura con id " + id + " cancellata correttamente";
        } else {
            throw new NotFoundException("Fattura con id " + id + " non trovata.");
        }
    }






}
