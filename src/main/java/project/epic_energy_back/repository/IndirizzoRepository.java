package project.epic_energy_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.epic_energy_back.entities.Indirizzo;
import project.epic_energy_back.enums.TipoSede;

public interface IndirizzoRepository extends JpaRepository<Indirizzo,Integer> {
    boolean existsByClienteIdAndTipoSede(Integer clienteId, TipoSede tipoSede);
}
