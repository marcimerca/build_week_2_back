package project.epic_energy_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.epic_energy_back.entity.anagrafe.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    Provincia findByNomeProvincia(String nomeProvincia);
}
