package schedeass.app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import schedeass.app.entities.Area;
import schedeass.app.entities.Dipendente;
import schedeass.app.entities.ObiettivoIndividuale;
import schedeass.app.entities.ObiettivoStrategico;
import schedeass.app.repositories.AreaRepository;
import schedeass.app.repositories.DipendenteRepository;
import schedeass.app.repositories.ObiettivoIndividualeRepository;
import schedeass.app.repositories.ObiettivoStrategicoRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ObiettivoService {

    private final ObiettivoIndividualeRepository obiettivoIndividualeRepository;
    private final ObiettivoStrategicoRepository obiettivoStrategicoRepository;
    private final AreaRepository areaRepository;

    private final DipendenteRepository dipendenteRepository;

    public List<ObiettivoIndividuale> getOI(){
        return obiettivoIndividualeRepository.findAll();
    }
    public List<ObiettivoStrategico> getOS(){
        return obiettivoStrategicoRepository.findAll();
    }

    public int saveOI(Integer obiettivoStrategico,
                      String nome,
                      String responsabilePolitico,
                      String responsabile,
                      Integer area,
                      String tipologia,
                      String indicatore,
                      Integer peso,
                      Integer anno,
                      Integer dipendente){

        ObiettivoIndividuale obiettivoIndividuale = new ObiettivoIndividuale();

        obiettivoIndividuale.setObiettivoStrategico(obiettivoStrategicoRepository.findById(obiettivoStrategico).orElse(null));
        obiettivoIndividuale.setNome(nome);
        obiettivoIndividuale.setResponsabilePolitico(responsabilePolitico);
        obiettivoIndividuale.setResponsabile(responsabile);
        obiettivoIndividuale.setArea(areaRepository.findById(area).orElse(null));
        obiettivoIndividuale.setTipologia(tipologia);
        obiettivoIndividuale.setIndicatore(indicatore);
        obiettivoIndividuale.setPeso(peso);
        obiettivoIndividuale.setAnno(anno);

        if(dipendente != null){
            Dipendente dipendenteDaAggiungere = dipendenteRepository.findById(dipendente).orElse(null);
            obiettivoIndividuale.setDipendente(dipendenteDaAggiungere);
        }
        else {
            obiettivoIndividuale.setDipendente(null);
        }

        obiettivoIndividualeRepository.save(obiettivoIndividuale);
        return 1;
    }

    public List<ObiettivoIndividuale> getOIFromMatricola(Integer matricola){

        return obiettivoIndividualeRepository.findByDipendente_Matricola(matricola);

    }

    public int saveOS(Integer area,
                      String tipologia,
                      String nome,
                      String presidio,
                      String stakeholder,
                      Integer anno){

        ObiettivoStrategico obiettivoStrategico = new ObiettivoStrategico();

        obiettivoStrategico.setNome(nome);
        obiettivoStrategico.setTipologia(tipologia);
        obiettivoStrategico.setPresidio(presidio);
        obiettivoStrategico.setStakeholder(stakeholder);
        obiettivoStrategico.setAnno(anno);

        Area areaDaAggiungere = areaRepository.findById(area).orElse(null);
        obiettivoStrategico.setArea(areaDaAggiungere);

        obiettivoStrategicoRepository.save(obiettivoStrategico);
        return 1;
    }

    @Transactional
    public void deleteOS(Integer codice){ obiettivoStrategicoRepository.deleteById(codice); }

    @Transactional
    public void deleteOI(Integer codice){ obiettivoIndividualeRepository.deleteById(codice); }

    public int assegnaOI(Integer codice, Integer matricola){

        ObiettivoIndividuale obiettivoIndividualeDaAggiornare = obiettivoIndividualeRepository.findById(codice).orElse(null);

        if(matricola != null){
        obiettivoIndividualeDaAggiornare.setDipendente(dipendenteRepository.findById(matricola).orElse(null));}
        else {
            obiettivoIndividualeDaAggiornare.setDipendente(null);
        }

        ObiettivoIndividuale obiettivoIndividualeAggiornato = obiettivoIndividualeRepository.save(obiettivoIndividualeDaAggiornare);
        return 1;
    }

    @Transactional
    public void deleteOiByOs(Integer codice){
        obiettivoIndividualeRepository.deleteByObiettivoStrategico_Codice(codice);
    }
}
