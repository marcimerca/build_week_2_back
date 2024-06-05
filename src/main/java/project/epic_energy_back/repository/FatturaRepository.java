package project.epic_energy_back.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.epic_energy_back.entities.Cliente;
import project.epic_energy_back.entities.Fattura;
import project.epic_energy_back.enums.STATO_FATTURA;

import java.time.LocalDate;

public interface FatturaRepository extends JpaRepository <Fattura, Integer> {


    Page<Fattura> findByCliente(Cliente cliente, Pageable pageable);

    Page<Fattura> findByStatoFattura(STATO_FATTURA statoFattura, Pageable pageable);

    Page<Fattura> findByDataImporto(LocalDate dataImporto, Pageable pageable);

    Page<Fattura> findByImportoBetween(Double importoMin, Double importoMax, Pageable pageable);

    @Query("SELECT f FROM Fattura f WHERE EXTRACT(YEAR FROM f.dataImporto) = :anno")
    Page<Fattura> findByAnno(Integer anno, Pageable pageable);

}
