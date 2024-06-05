package project.epic_energy_back.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.epic_energy_back.dto.ClienteDTO;
import project.epic_energy_back.dto.UtenteDTO;
import project.epic_energy_back.entities.Cliente;
import project.epic_energy_back.entities.Utente;
import project.epic_energy_back.exceptions.BadRequestException;
import project.epic_energy_back.exceptions.NotFoundException;
import project.epic_energy_back.repository.ClienteRepository;
import project.epic_energy_back.service.ClienteService;

import java.io.IOException;
import java.time.LocalDate;
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
    public Page<Cliente> getAllclienti(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "15") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {

        return clienteService.getAllClienti(page, size, sortBy);
    }

    @GetMapping("/clienti/provinciaLegale")
    public Page<Cliente> getClientiOrdinatiPerProvinciaSedeLegale(@PageableDefault(size = 10) Pageable pageable) {
        return clienteService.getClientiOrdinatiPerProvinciaSedeLegale(pageable);
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

    @PatchMapping("/clienti/{id}")
    public String patchLogoCliente(@PathVariable int id, MultipartFile foto) throws IOException {
        return clienteService.patchLogoAzienda(id, foto);
    }


    @GetMapping("/clienti/filter")
    public Page<Cliente> filterClienti(
            @RequestParam(required = false) String ragioneSociale,
            @RequestParam(required = false) Double fatturatoAnnuale,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInserimento,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataUltimoContatto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return clienteService.filterClienti(ragioneSociale, fatturatoAnnuale, dataInserimento, dataUltimoContatto, pageable);
    }


}
