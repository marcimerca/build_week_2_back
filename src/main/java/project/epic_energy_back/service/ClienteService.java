package project.epic_energy_back.service;


import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import project.epic_energy_back.controller.ClienteController;
import project.epic_energy_back.dto.ClienteDTO;
import project.epic_energy_back.dto.UtenteDTO;
import project.epic_energy_back.entities.Cliente;
import project.epic_energy_back.entities.Utente;
import project.epic_energy_back.exceptions.BadRequestException;
import project.epic_energy_back.exceptions.NotFoundException;
import project.epic_energy_back.repository.ClienteRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Autowired
    private Cloudinary cloudinary;

   /* @Autowired
    private JavaMailSenderImpl javaMailSender;
*/

    public String saveCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setNomeContatto(clienteDTO.getNomeContatto());
        cliente.setCognomeContatto(clienteDTO.getCognomeContatto());
        cliente.setPec(clienteDTO.getPec());
        cliente.setDataInserimento(clienteDTO.getDataInserimento());
        cliente.setDataUltimoContatto(clienteDTO.getDataUltimoContatto());
        cliente.setFatturatoAnnuale(clienteDTO.getFatturatoAnnuale());
        cliente.setEmailContatto(clienteDTO.getEmailContatto());
        cliente.setLogoAziendale(clienteDTO.getLogoAziendale());
        cliente.setPartitaIva(clienteDTO.getPartitaIva());
        cliente.setRagioneSociale(clienteDTO.getRagioneSociale());
        cliente.setTipoSocieta(clienteDTO.getTipoSocieta());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setTelefonoContatto(clienteDTO.getTelefonoContatto());


        clienteRepository.save(cliente);
        return "Cliente with id=" + cliente.getId() + " correctly saved";


    }

    public Page<Cliente> getAllClienti (int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return clienteRepository.findAll(pageable);

    }
    public Page<Cliente> getAllClientiByProvinciaSedeLegale (int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return clienteRepository.findAll(pageable);

    }




    public Optional<Cliente> getClienteById(int id){
        return clienteRepository.findById(id);
    }


    public Cliente updateCliente(int id,  ClienteDTO clienteDTO){
       Optional<Cliente> clienteOptional = clienteRepository.findById(id);
       if(clienteOptional.isPresent()){
           Cliente cliente = clienteOptional.get();
           cliente.setEmail(clienteDTO.getEmail());
           cliente.setNomeContatto(clienteDTO.getNomeContatto());
           cliente.setCognomeContatto(clienteDTO.getCognomeContatto());
           cliente.setPec(clienteDTO.getPec());
           cliente.setDataInserimento(clienteDTO.getDataInserimento());
           cliente.setDataUltimoContatto(clienteDTO.getDataUltimoContatto());
           cliente.setFatturatoAnnuale(clienteDTO.getFatturatoAnnuale());
           cliente.setEmailContatto(clienteDTO.getEmailContatto());
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


    public Page<Cliente> getClientiOrdinatiPerProvinciaSedeLegale(Pageable pageable) {
        return clienteRepository.findAllOrderByProvinciaSedeLegale(pageable);
    }

    public String patchLogoAzienda(int id, MultipartFile foto) throws IOException {
        Optional<Cliente> clienteOptional = getClienteById(id);
        if (clienteOptional.isPresent()) {
            String url = (String) cloudinary.uploader().upload(foto.getBytes(), Collections.emptyMap()).get("url");
           Cliente cliente = clienteOptional.get();
            cliente.setLogoAziendale(url);
            clienteRepository.save(cliente);
            return "Logo aziendale con url " + url + " salvato e associata correttamente al cliente con id " + id;
        } else {
            throw new NotFoundException("Il cliente con id " + id + " non è stato trovato");
        }
    }

  /*  private void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione");
        message.setText("Registrazione al servizio rest avvenuta con successo");

        javaMailSender.send(message);
    }*/










}
