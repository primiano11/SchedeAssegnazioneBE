package schedeass.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import schedeass.app.entities.Area;
import schedeass.app.entities.Dipendente;
import schedeass.app.entities.ObiettivoIndividuale;
import schedeass.app.entities.ObiettivoStrategico;
import schedeass.app.services.ObiettivoService;

import java.util.List;

@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/api/obiettivi")
public class ObiettivoController {

    @Autowired
    private ObiettivoService obiettivoService;

    @GetMapping(path = "getalloi")
    @ResponseStatus(HttpStatus.OK)
    public List<ObiettivoIndividuale> getOI(){
        return obiettivoService.getOI();
    }

    @PostMapping(path = "saveoi")
    @ResponseStatus(HttpStatus.OK)
    public String saveOI(
            @RequestParam Integer obiettivoStrategico,
            @RequestParam String nome,
            @RequestParam String responsabilePolitico,
            @RequestParam String responsabile,
            @RequestParam Integer area,
            @RequestParam String tipologia,
            @RequestParam String indicatore,
            @RequestParam Integer peso,
            @RequestParam Integer anno,
            @RequestParam(required = false) Integer dipendente
    ){
        ObiettivoIndividuale obiettivoIndividuale = new ObiettivoIndividuale();
        obiettivoService.saveOI(obiettivoStrategico, nome, responsabilePolitico, responsabile, area, tipologia, indicatore, peso, anno, dipendente);
        return "SUCCESS";
    }


    @GetMapping(path = "getoifrommatricola")
    @ResponseStatus(HttpStatus.OK)
    public List<ObiettivoIndividuale> getOIFromMatricola(@RequestParam Integer matricola){
        return obiettivoService.getOIFromMatricola(matricola);
    }

    @GetMapping(path = "getallos")
    @ResponseStatus(HttpStatus.OK)
    public List<ObiettivoStrategico> getOS(){
        return obiettivoService.getOS();
    }

    @PostMapping(path = "saveos")
    @ResponseStatus(HttpStatus.OK)
    public String saveOS(
            @RequestParam Integer area,
            @RequestParam String tipologia,
            @RequestParam String nome,
            @RequestParam String presidio,
            @RequestParam String stakeholder,
            @RequestParam Integer anno
    ){
        ObiettivoIndividuale obiettivoIndividuale = new ObiettivoIndividuale();
        obiettivoService.saveOS(area, tipologia, nome, presidio, stakeholder, anno);
        return "SUCCESS";
    }

    @PostMapping(path = "deleteoi")
    @ResponseStatus(HttpStatus.OK)
    public String deleteOI( @RequestParam Integer codice){
        obiettivoService.deleteOI(codice);
        return "SUCCESS";
    }

    @PostMapping(path = "deleteos")
    @ResponseStatus(HttpStatus.OK)
    public String deleteOS( @RequestParam Integer codice){
        obiettivoService.deleteOiByOs(codice);
        obiettivoService.deleteOS(codice);
        return "SUCCESS";
    }


    @PostMapping(path = "assegnaoi")
    @ResponseStatus(HttpStatus.OK)
    public String assegnaOI( @RequestParam Integer codice,
                            @RequestParam(required = false) Integer matricola){
        obiettivoService.assegnaOI(codice, matricola);
        return "SUCCESS";
    }
}
