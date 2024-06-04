package project.epic_energy_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.epic_energy_back.entities.Cliente;
import project.epic_energy_back.entities.Fattura;

public interface FatturaRepository extends JpaRepository <Fattura, Integer> {

}
