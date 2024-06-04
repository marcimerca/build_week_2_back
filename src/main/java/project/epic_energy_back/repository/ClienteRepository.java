package project.epic_energy_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.epic_energy_back.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {



}
