package schedeass.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import schedeass.app.entities.ObiettivoIndividuale;
import schedeass.app.entities.ObiettivoStrategico;

import java.util.List;

public interface ObiettivoIndividualeRepository extends JpaRepository<ObiettivoIndividuale, Integer> {

    List<ObiettivoIndividuale> findByDipendente_Matricola(Integer matricola);

    long deleteByObiettivoStrategico_Codice(Integer codice);




}
