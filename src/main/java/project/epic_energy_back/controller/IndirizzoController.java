package project.epic_energy_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.epic_energy_back.dto.anagrafeDto.IndirizzoAnagrafeDto;
import project.epic_energy_back.exceptions.BadRequestException;
import project.epic_energy_back.service.IndirizzoService;

@RestController
@RequestMapping("/indirizzo")


public class IndirizzoController {
    @Autowired
    private IndirizzoService indirizzoService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public String saveIndirizzo(@RequestBody IndirizzoAnagrafeDto indirizzoAnagrafeDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
        return indirizzoService.saveIndirizzo(indirizzoAnagrafeDto);
    }
}
