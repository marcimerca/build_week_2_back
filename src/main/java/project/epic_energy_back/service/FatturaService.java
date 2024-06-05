package project.epic_energy_back.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import project.epic_energy_back.enums.STATO_FATTURA;
import project.epic_energy_back.exceptions.BadRequestException;
import project.epic_energy_back.exceptions.NotFoundException;
import project.epic_energy_back.repository.FatturaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FatturaService {

    @Autowired
    private FatturaRepository fatturaRepository;
    @Autowired
    private ClienteService clienteService;

    public String saveFattura(FatturaDTO fatturaDTO){

        Fattura fattura = new Fattura();

        fattura.setDataImporto(fatturaDTO.getDataImporto());
        fattura.setImporto(fatturaDTO.getImporto());
        fattura.setNumero(fatturaDTO.getNumero());
        fattura.setStatoFattura(fatturaDTO.getStatoFattura());
        Optional<Cliente> clienteOptional = clienteService.getClienteById(fatturaDTO.getIdCliente());
        if (!clienteOptional.isPresent()) {
            throw new RuntimeException("Id del cliente non trovato, fattura non caricata");
        }
        fattura.setCliente(clienteOptional.get());

        fatturaRepository.save(fattura);
        return "Fattura with id=" + fattura.getId() + " correctly saved";
    }

    public Page<Fattura> getAllFatture(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return fatturaRepository.findAll(pageable);

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
            Optional<Cliente> clienteOptional = clienteService.getClienteById(fatturaDTO.getIdCliente());
            if (!clienteOptional.isPresent()) {
                throw new RuntimeException("Id del cliente non trovato, fattura non caricata");
            }
            fattura.setCliente(clienteOptional.get());
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


    public Page<Fattura> getFattureByCliente(Cliente cliente, Pageable pageable) {
        return fatturaRepository.findByCliente(cliente, pageable);
    }

    public Page<Fattura> getFattureByStatoFattura(STATO_FATTURA statoFattura, Pageable pageable) {
        return fatturaRepository.findByStatoFattura(statoFattura, pageable);
    }

    public Page<Fattura> getFattureByDataImporto(LocalDate dataImporto, Pageable pageable) {
        return fatturaRepository.findByDataImporto(dataImporto, pageable);
    }

    public Page<Fattura> getFattureByImportoBetween(Double importoMin, Double importoMax, Pageable pageable) {
        return fatturaRepository.findByImportoBetween(importoMin, importoMax, pageable);
    }

    public Page<Fattura> getFattureByAnno(Integer anno, Pageable pageable) {
        return fatturaRepository.findByAnno(anno, pageable);
    }

    public Page<Fattura> filterFatture(Cliente cliente, STATO_FATTURA statoFattura, LocalDate dataImporto,
                                       Double importoMin, Double importoMax, Integer anno, Pageable pageable) {
        if (cliente != null) {
            return getFattureByCliente(cliente, pageable);
        } else if (statoFattura != null) {
            return getFattureByStatoFattura(statoFattura, pageable);
        } else if (dataImporto != null) {
            return getFattureByDataImporto(dataImporto, pageable);
        } else if (importoMin != null && importoMax != null) {
            return getFattureByImportoBetween(importoMin, importoMax, pageable);
        } else if (anno != null) {
            return getFattureByAnno(anno, pageable);
        } else {
            return fatturaRepository.findAll(pageable);
        }
    }






}

