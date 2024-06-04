package repository;

import entities.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtentiRepository extends JpaRepository {

    public Optional<Utenti> findByEmail(String email);
}
