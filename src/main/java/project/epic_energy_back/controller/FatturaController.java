package project.epic_energy_back.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.epic_energy_back.dto.ClienteDTO;
import project.epic_energy_back.dto.FatturaDTO;
import project.epic_energy_back.entities.Cliente;
import project.epic_energy_back.entities.Fattura;
import project.epic_energy_back.exceptions.BadRequestException;
import project.epic_energy_back.exceptions.NotFoundException;
import project.epic_energy_back.repository.FatturaRepository;
import project.epic_energy_back.service.FatturaService;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

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








}
