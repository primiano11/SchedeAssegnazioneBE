package schedeass.app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import schedeass.app.entities.Area;
import schedeass.app.entities.Dipendente;
import schedeass.app.repositories.AreaRepository;
import schedeass.app.repositories.DipendenteRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;
    private final AreaRepository areaRepository;


    public List<Dipendente> getDipendenti(){
        return dipendenteRepository.findAll();
    }

    public List<Dipendente> getDipendentiByArea(Integer codice){
        return dipendenteRepository.findByUnitaOrganizzativa_Codice(codice);
    }

    @Transactional
    public int saveDipendente(String nome, String cognome, Integer unitaOrganizzativa){

        Dipendente dipendente = new Dipendente();

        dipendente.setNome(nome);
        dipendente.setCognome(cognome);
        dipendente.setUnitaOrganizzativa(areaRepository.findById(unitaOrganizzativa).orElse(null));

        dipendenteRepository.save(dipendente);
        return 1;
    }

    public int updateDipendente(Integer matricola, String nome, String cognome, Integer unitaOrganizzativa){

        Dipendente dipendenteDaAggiornare = dipendenteRepository.findById(matricola).orElse(null);

        if (dipendenteDaAggiornare == null){
            return 0;
        }

        dipendenteDaAggiornare.setNome(nome);
        dipendenteDaAggiornare.setCognome(cognome);
        dipendenteDaAggiornare.setUnitaOrganizzativa(areaRepository.findById(unitaOrganizzativa).orElse(null));

        Dipendente dipendenteAggiornato = dipendenteRepository.save(dipendenteDaAggiornare);
        return 1;
    }

    @Transactional
    public void deleteDipendente(Integer matricola){
        dipendenteRepository.deleteById(matricola);
    }
}
