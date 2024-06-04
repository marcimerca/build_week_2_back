package service;


import dto.UtenteLoginDTO;
import entities.Utenti;
import exceptions.NotFoundException;
import exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.JwtTool;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UtentiService utentiService;

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateUtenteAndCreateToken(UtenteLoginDTO utenteLoginDto) {
        Optional<Utenti> utenteOptional = utentiService.getUtenteByEmail(utenteLoginDto.getEmail());

        if (utenteOptional.isPresent()) {
            Utenti utente = utenteOptional.get();
            if (passwordEncoder.matches(utenteLoginDto.getPassword(), utente.getPassword())) {
                return jwtTool.createToken(utente);
            } else {
                throw new UnauthorizedException("Errore nel login, riloggarsi");
            }

        } else {
            throw new NotFoundException("Utente con email " + utenteLoginDto.getEmail() + "non trovato ");
        }
    }
}