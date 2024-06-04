package project.epic_energy_back.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.epic_energy_back.controller.ClienteController;
import project.epic_energy_back.dto.ClienteDTO;
import project.epic_energy_back.dto.UtenteDTO;
import project.epic_energy_back.entities.Cliente;
import project.epic_energy_back.exceptions.BadRequestException;
import project.epic_energy_back.exceptions.NotFoundException;
import project.epic_energy_back.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public String saveCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setFatture(clienteDTO.getFatture());
        cliente.setNomeContatto(clienteDTO.getNomeContatto());
        cliente.setCognomeContatto(clienteDTO.getCognomeContatto());
        cliente.setPec(clienteDTO.getPec());
        cliente.setDataInserimento(clienteDTO.getDataInserimento());
        cliente.setDataUltimoContatto(clienteDTO.getDataUltimoContatto());
        cliente.setFatturatoAnnuale(clienteDTO.getFatturatoAnnuale());
        cliente.setEmailContatto(clienteDTO.getEmailContatto());
        cliente.setIndirizzi(clienteDTO.getIndirizzi());
        cliente.setLogoAziendale(clienteDTO.getLogoAziendale());
        cliente.setPartitaIva(clienteDTO.getPartitaIva());
        cliente.setRagioneSociale(clienteDTO.getRagioneSociale());
        cliente.setTipoSocieta(clienteDTO.getTipoSocieta());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setTelefonoContatto(clienteDTO.getTelefonoContatto());


        clienteRepository.save(cliente);
        return "Cliente with id=" + cliente.getId() + " correctly saved";


    }

    public List<Cliente> getAllClienti (){

        return clienteRepository.findAll();

    }

    public Optional<Cliente> getClienteById(int id){
        return clienteRepository.findById(id);
    }


    public Cliente updateCliente(int id,  ClienteDTO clienteDTO){
       Optional<Cliente> clienteOptional = clienteRepository.findById(id);
       if(clienteOptional.isPresent()){
           Cliente cliente = clienteOptional.get();
           cliente.setEmail(clienteDTO.getEmail());
           cliente.setFatture(clienteDTO.getFatture());
           cliente.setNomeContatto(clienteDTO.getNomeContatto());
           cliente.setCognomeContatto(clienteDTO.getCognomeContatto());
           cliente.setPec(clienteDTO.getPec());
           cliente.setDataInserimento(clienteDTO.getDataInserimento());
           cliente.setDataUltimoContatto(clienteDTO.getDataUltimoContatto());
           cliente.setFatturatoAnnuale(clienteDTO.getFatturatoAnnuale());
           cliente.setEmailContatto(clienteDTO.getEmailContatto());
           cliente.setIndirizzi(clienteDTO.getIndirizzi());
           cliente.setLogoAziendale(clienteDTO.getLogoAziendale());
           cliente.setPartitaIva(clienteDTO.getPartitaIva());
           cliente.setRagioneSociale(clienteDTO.getRagioneSociale());
           cliente.setTipoSocieta(clienteDTO.getTipoSocieta());
           cliente.setTelefono(clienteDTO.getTelefono());
           cliente.setTelefonoContatto(clienteDTO.getTelefonoContatto());

           clienteRepository.save(cliente);
           return cliente;
       }else {
           throw new NotFoundException("Cliente con id " + id + " non trovato.");
       }

    }


    public String deleteCliente( int id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            clienteRepository.delete(clienteOptional.get());
            return "Cliente con id " + id + " cancellato correttamente";
        } else {
            throw new NotFoundException("Cliente con id " + id + " non trovato.");
        }

    }










}
