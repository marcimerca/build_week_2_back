package project.epic_energy_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.epic_energy_back.dto.UtenteDTO;
import project.epic_energy_back.entities.Utente;
import project.epic_energy_back.exceptions.BadRequestException;
import project.epic_energy_back.exceptions.NotFoundException;
import project.epic_energy_back.repository.UtenteRepository;
import project.epic_energy_back.service.UtenteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

   // @Autowired
    //private UtenteRepository utenteRepository;

    @GetMapping("/api/users")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Utente> getAllUtenti(){

        return utenteService.getAllUtenti();
    }

    @GetMapping("/api/users/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Utente getUserById(@PathVariable int id){
        Optional<Utente> utenteOptional = utenteService.getUtenteById(id);

        if(utenteOptional.isPresent()){
            return utenteOptional.get();
        }
        else{
            throw new NotFoundException("User with id=" + id + " not found");
        }
    }

    @PutMapping("/api/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente updateUtente(@PathVariable int id, @RequestBody @Validated UtenteDTO utenteDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error->error.getDefaultMessage()).
                    reduce("", (s, s2) -> s+s2));
        }

        return utenteService.updateUtente(id, utenteDTO);
    }

    @DeleteMapping("/api/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUtente(@PathVariable int id){
        return utenteService.deleteUtente(id);
    }






}