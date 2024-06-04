package project.epic_energy_back.repository;

import project.epic_energy_back.entities.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtentiRepository extends JpaRepository<Utenti,Integer> {

    public Optional<Utenti> findByEmail(String email);
}
