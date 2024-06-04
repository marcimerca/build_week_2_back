package project.epic_energy_back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.epic_energy_back.dto.anagrafeDto.AnagrafeDto;
import project.epic_energy_back.entities.anagrafe.Comune;
import project.epic_energy_back.entities.anagrafe.Provincia;
import project.epic_energy_back.repository.AnagrafeRepository;
import project.epic_energy_back.repository.ProvinciaRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class AnagrafeService {

    @Autowired
    private AnagrafeRepository anagrafeRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public String saveAnagrafe(AnagrafeDto anagrafeDto) {
        try {
            BufferedReader comuniReader = new BufferedReader(new InputStreamReader(anagrafeDto.getComuniFile().getInputStream()));
            BufferedReader provinceReader = new BufferedReader(new InputStreamReader(anagrafeDto.getProvinceFile().getInputStream()));
            while (provinceReader.ready()) {
                String line = provinceReader.readLine();
                String[] blocco = line.split(";");
                if (blocco.length != 3) {
                    System.err.println("Invalid line: " + line);
                    continue;
                }

                try {
                    String siglaProvincia = blocco[0];
                    String nomeProvincia = blocco[1];
                    String regione = blocco[2];

                    Provincia provincia = new Provincia();
                    provincia.setSiglaProvincia(siglaProvincia);
                    provincia.setRegione(regione);
                    provincia.setNomeProvincia(nomeProvincia);

                    provinciaRepository.save(provincia);
                 //   System.out.println(line);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in line: " + line);
                }
            }
                    provinciaRepository.flush();//mi salva il file subito se no provincia_id è null perche aspetta il salvataggio dopo il secondo cilo

            // Leggi il file dei comuni, associa ogni comune a una provincia e salva ogni comune nel database
            while (comuniReader.ready()) {
                String line = comuniReader.readLine();
                String[] blocco = line.split(";");
                if (blocco.length != 4) {
                    System.err.println("Invalid line: " + line);
                    continue;
                }

                try {
                    Long pfProvincia = Long.parseLong(blocco[0]);
                    Long pfComune = Long.parseLong(blocco[1]);
                    String nomeComune = blocco[2];
                    String nomeProvincia = blocco[3];

                    Provincia provincia =(Provincia) provinciaRepository.findByNomeProvincia(nomeProvincia);
                    if (provincia == null) {
                        System.err.println("Provincia not found: " + nomeProvincia);
                        continue;
                    }else {
                        System.out.println("Provincia trovata: " + provincia.getNomeProvincia());
                    Comune comune = new Comune();
                    comune.setPfProvincia(pfProvincia);
                    comune.setPfComune(pfComune);
                    comune.setNomeComune(nomeComune);
                    comune.setProvincia(provincia);

                    anagrafeRepository.save(comune);
                    }

                  //  System.out.println(line);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format in line: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "Comuni data saved successfully";
    }
}