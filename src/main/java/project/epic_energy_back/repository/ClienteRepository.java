package project.epic_energy_back.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.epic_energy_back.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    /*@Query("SELECT c FROM Cliente c JOIN c.indirizzi i WHERE i.tipoSede = 'LEGALE' ORDER BY i.localita.provincia ASC")
    Page<Cliente> findAllOrderByProvinciaSedeLegale(Pageable pageable);
*/
    @Query("SELECT i.cliente FROM Indirizzo i WHERE i.tipoSede = 'LEGALE' ORDER BY i.localita.provincia ASC")
    Page<Cliente> findAllOrderByProvinciaSedeLegale(Pageable pageable);



}
