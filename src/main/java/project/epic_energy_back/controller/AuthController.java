package project.epic_energy_back.controller;

import project.epic_energy_back.dto.UtenteLoginDTO;
import project.epic_energy_back.dto.UtentiDTO;
import project.epic_energy_back.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.epic_energy_back.service.AuthService;
import project.epic_energy_back.service.UtentiService;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UtentiService utentiService;

    @PostMapping("/auth/register")
    public String register(@RequestBody @Validated UtentiDTO utentiDTO, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }

        return utentiService.saveUser(utentiDTO);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated UtenteLoginDTO utenteLoginDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }

        return authService.authenticateUtenteAndCreateToken(utenteLoginDTO);

    }
}
