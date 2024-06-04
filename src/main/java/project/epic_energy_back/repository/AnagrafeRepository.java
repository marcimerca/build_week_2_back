package project.epic_energy_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.epic_energy_back.entities.anagrafe.AnagrafeCenter;

public interface AnagrafeRepository extends JpaRepository<AnagrafeCenter, Integer>{
}