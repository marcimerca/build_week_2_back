package project.epic_energy_back.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.epic_energy_back.dto.ClienteDTO;
import project.epic_energy_back.dto.FatturaDTO;
import project.epic_energy_back.entities.Cliente;
import project.epic_energy_back.entities.Fattura;
import project.epic_energy_back.enums.STATO_FATTURA;
import project.epic_energy_back.exceptions.BadRequestException;
import project.epic_energy_back.exceptions.NotFoundException;
import project.epic_energy_back.repository.FatturaRepository;
import project.epic_energy_back.service.ClienteService;
import project.epic_energy_back.service.FatturaService;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/fatture")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String saveFattura(@RequestBody @Validated FatturaDTO fatturaDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error->error.getDefaultMessage()).
                    reduce("", (s, s2) -> s+s2));
        }

        return fatturaService.saveFattura(fatturaDTO);
    }

    @GetMapping("/fatture")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Page<Fattura> getAllFatture(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "15") int size,
                                       @RequestParam(defaultValue = "id") String sortBy){

        return fatturaService.getAllFatture(page,size,sortBy);
    }



    @GetMapping("/fatture/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Fattura getFatturaById(@PathVariable int id){
        Optional<Fattura> fatturaOptional = fatturaService.getFatturaById(id);

        if(fatturaOptional.isPresent()){
            return fatturaOptional.get();
        }
        else{
            throw new NotFoundException("Fattura with id=" + id + " not found");
        }
    }

    @PutMapping("/fatture/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Fattura updateFattura(@PathVariable int id, @RequestBody @Validated FatturaDTO fatturaDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error->error.getDefaultMessage()).
                    reduce("", (s, s2) -> s+s2));
        }

        return fatturaService.updateFattura(id, fatturaDTO);
    }

    @DeleteMapping("/fatture/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteFattura(@PathVariable int id){
        return fatturaService.deleteFattura(id);
    }

    @GetMapping("/fatture/filter")
    public Page<Fattura> filterFatture(
            @RequestParam(required = false) Integer clienteId,
            @RequestParam(required = false) String statoFattura,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataImporto,
            @RequestParam(required = false) Double importoMin,
            @RequestParam(required = false) Double importoMax,
            @RequestParam(required = false) Integer anno,
            Pageable pageable) {

        Cliente cliente = null;
        if (clienteId != null) {
            Optional<Cliente> clienteOptional = clienteService.getClienteById(clienteId);
            if (clienteOptional.isPresent()) {
                cliente = clienteOptional.get();
            } else {
                throw new NotFoundException("Cliente non trovato con ID: " + clienteId);
            }
        }

        STATO_FATTURA stato = null;
        if (statoFattura != null) {
            stato = STATO_FATTURA.valueOf(statoFattura.toUpperCase());
        }

        return fatturaService.filterFatture(cliente, stato, dataImporto, importoMin, importoMax, anno, pageable);
    }








}
