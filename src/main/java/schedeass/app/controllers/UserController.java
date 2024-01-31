package schedeass.app.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import schedeass.app.entities.User;
import schedeass.app.services.UserService;
import schedeass.app.utils.AES;
import schedeass.app.utils.Utilities;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final String key = "SchedeAssPT";
    @Autowired
    private UserService userService;

    @PostMapping(path = "register")
    @ResponseStatus(HttpStatus.OK)
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password) {

        String response = "";

        password = AES.encrypt(password, key);

        int flag = userService.registerUser(username, email, password);

        if (flag == 2) {
            response = Utilities.constructJSONMessage("register", true, "Registrazione avvenuta con successo");
            return response;
        }
        if (flag == 0) {
            response = Utilities.constructJSONMessage("register", false, "ERRORE: Compilare tutti i campi!");
            return response;
        }
        if (flag == 1) {
            response = Utilities.constructJSONMessage("register", false, "Sei già registrato!");
            return response;
        }

        return response;
    }


    @GetMapping(path = "login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestParam String email,
                        @RequestParam String password) {

        JSONObject jsonObject = new JSONObject();
        User user = userService.login(email, password);

        if (user != null) {
            jsonObject.put("tag", "login");
            jsonObject.put("status", true);
            jsonObject.put("id", user.getId());
            jsonObject.put("username", user.getUsername());
            jsonObject.put("message", "Utente trovato");
        } else {
            jsonObject.put("tag", "login");
            jsonObject.put("status", false);
            jsonObject.put("id", 0);
            jsonObject.put("username", "");
            jsonObject.put("message", "Credenziali errate");
        }

        return jsonObject.toString();

    }


}
