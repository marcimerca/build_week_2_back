package project.epic_energy_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.epic_energy_back.dto.anagrafeDto.IndirizzoAnagrafeDto;
import project.epic_energy_back.entities.Indirizzo;
import project.epic_energy_back.exceptions.BadRequestException;
import project.epic_energy_back.service.IndirizzoService;

import java.util.List;

@RestController



public class IndirizzoController {
    @Autowired
    private IndirizzoService indirizzoService;

    @PostMapping("/indirizzi")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public String saveIndirizzo(@RequestBody IndirizzoAnagrafeDto indirizzoAnagrafeDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
        return indirizzoService.saveIndirizzo(indirizzoAnagrafeDto);
    }
    @GetMapping("/indirizzi")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Page<Indirizzo> getAllIndirizzi(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "15") int size,
                                           @RequestParam(defaultValue = "id") String sortBy){
        return indirizzoService.getAllIndirizzi(page,size,sortBy);
    }
}
