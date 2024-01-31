package schedeass.app.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import schedeass.app.entities.Dipendente;
import schedeass.app.services.DipendenteService;
import schedeass.app.utils.SuccessResponse;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/api/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;


    @GetMapping(path = "getall")
    @ResponseStatus(HttpStatus.OK)
    public List<Dipendente> getDipendenti(){
        return dipendenteService.getDipendenti();
    }


    @PostMapping(path = "save")
    @ResponseStatus(HttpStatus.OK)
    public String saveDipendente(@RequestParam String nome,
                                 @RequestParam String cognome,
                                 @RequestParam Integer unitaOrganizzativa){
        Dipendente dipendente = new Dipendente();
        dipendenteService.saveDipendente(nome, cognome, unitaOrganizzativa);
        return "SUCCESS";
    }


    @PostMapping(path = "update")
    @ResponseStatus(HttpStatus.OK)
    public String updateDipendente(@RequestParam Integer matricola,
                                   @RequestParam String nome,
                                   @RequestParam String cognome,
                                   @RequestParam Integer unitaOrganizzativa){
        dipendenteService.updateDipendente(matricola, nome, cognome, unitaOrganizzativa);
        return "SUCCESS";
    }


    @PostMapping(path = "delete")
    @ResponseStatus(HttpStatus.OK)
    public String deleteDipendente( @RequestParam Integer matricola){
        dipendenteService.deleteDipendente(matricola);
        return "SUCCESS";
    }

    @GetMapping(path = "getbyarea")
    @ResponseStatus(HttpStatus.OK)
    public List<Dipendente> getDipendentiByArea(@RequestParam Integer codice){
        return dipendenteService.getDipendentiByArea(codice);
    }


}
