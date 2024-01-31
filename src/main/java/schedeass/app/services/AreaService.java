package schedeass.app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import schedeass.app.entities.Area;
import schedeass.app.entities.Dipendente;
import schedeass.app.repositories.AreaRepository;
import schedeass.app.repositories.DipendenteRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;

    public List<Area> getAree(){
        return areaRepository.findAll();
    }

    @Transactional
    public int saveArea(String nome, String tipologia, String descrizione, String stakeholder, Integer anno){

        Area area = new Area();

        area.setNome(nome);
        area.setTipologia(tipologia);
        area.setDescrizione(descrizione);
        area.setStakeholder(stakeholder);
        area.setAnno(anno);

        areaRepository.save(area);
        return 1;
    }

    public int updateArea(Integer codice,
                          String nome,
                          String tipologia,
                          String descrizione,
                          String stakeholder,
                          Integer anno){

        Area areaDaAggiornare = areaRepository.findById(codice).orElse(null);

        if (areaDaAggiornare == null){
            return 0;
        }

        areaDaAggiornare.setNome(nome);
        areaDaAggiornare.setTipologia(tipologia);
        areaDaAggiornare.setDescrizione(descrizione);
        areaDaAggiornare.setStakeholder(stakeholder);
        areaDaAggiornare.setAnno(anno);

        Area areaAggiornata = areaRepository.save(areaDaAggiornare);
        return 1;
    }

    public void deleteArea(Integer codice){ areaRepository.deleteById(codice);}
}
