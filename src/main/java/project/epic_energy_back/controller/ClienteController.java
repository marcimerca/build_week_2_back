package project.epic_energy_back.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.epic_energy_back.dto.ClienteDTO;
import project.epic_energy_back.dto.UtenteDTO;
import project.epic_energy_back.entities.Cliente;
import project.epic_energy_back.entities.Utente;
import project.epic_energy_back.exceptions.BadRequestException;
import project.epic_energy_back.exceptions.NotFoundException;
import project.epic_energy_back.repository.ClienteRepository;
import project.epic_energy_back.service.ClienteService;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @PostMapping("/clienti")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String saveCliente(@RequestBody @Validated ClienteDTO clienteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
        return clienteService.saveCliente(clienteDTO);
    }


    @GetMapping("/clienti")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<Cliente> getAllclienti() {

        return clienteService.getAllClienti();
    }

    @GetMapping("/clienti/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Cliente getClienteById(@PathVariable int id) {
        Optional<Cliente> clienteOptional = clienteService.getClienteById(id);

        if (clienteOptional.isPresent()) {
            return clienteOptional.get();
        } else {
            throw new NotFoundException("User with id=" + id + " not found");
        }
    }

    @PutMapping("/clienti/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente updateCliente(@PathVariable int id, @RequestBody @Validated ClienteDTO clienteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }

        return clienteService.updateCliente(id, clienteDTO);
    }

    @DeleteMapping("/clienti/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteCliente(@PathVariable int id) {
        return clienteService.deleteCliente(id);
    }


}
