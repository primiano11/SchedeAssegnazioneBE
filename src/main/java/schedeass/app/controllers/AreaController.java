package schedeass.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import schedeass.app.entities.Area;
import schedeass.app.entities.Dipendente;
import schedeass.app.services.AreaService;

import java.util.List;

@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/api/aree")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping(path = "getall")
    @ResponseStatus(HttpStatus.OK)
    public List<Area> getDipendenti(){
        return areaService.getAree();
    }

    @PostMapping(path = "save")
    @ResponseStatus(HttpStatus.OK)
    public String saveArea(@RequestParam String nome,
                           @RequestParam String tipologia,
                           @RequestParam String descrizione,
                           @RequestParam String stakeholder,
                           @RequestParam Integer anno){
        Area area = new Area();
        areaService.saveArea(nome, tipologia, descrizione, stakeholder, anno);
        return "SUCCESS";
    }

    @PostMapping(path = "update")
    @ResponseStatus(HttpStatus.OK)
    public String updateArea(@RequestParam Integer codice,
                             @RequestParam String nome,
                             @RequestParam String tipologia,
                             @RequestParam String descrizione,
                             @RequestParam String stakeholder,
                             @RequestParam Integer anno){
        areaService.updateArea(codice, nome, tipologia, descrizione, stakeholder, anno);
        return "SUCCESS";
    }

    @PostMapping(path = "delete")
    @ResponseStatus(HttpStatus.OK)
    public String deleteArea( @RequestParam Integer codice){
        areaService.deleteArea(codice);
        return "SUCCESS";
    }
}
