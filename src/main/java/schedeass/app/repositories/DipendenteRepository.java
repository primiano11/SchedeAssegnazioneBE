package schedeass.app.repositories;

import schedeass.app.entities.Dipendente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DipendenteRepository extends JpaRepository<Dipendente, Integer>{
    List<Dipendente> findByUnitaOrganizzativa_Codice(Integer codice);




}
