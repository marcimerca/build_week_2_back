package service;


import dto.UtenteLoginDTO;
import dto.UtentiDTO;
import entities.Utenti;
import it.epicode.progetto.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.JwtTool;

@Service
public class AuthService {

    @Autowired
    private UtentiService userService;

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateUserAndCreateToken(UtentiDTO utentiDTO) {

        Utenti utenti = userService.getUserByEmail(UtenteLoginDTO.getEmail());

        if (passwordEncoder.matches(UtenteLoginDTO.getPassword(), user.getPassword())){
            return jwtTool.createToken(user);
        }else {
            throw new UnauthorizedException("Error in authorization, relogin!");
        }
    }
}