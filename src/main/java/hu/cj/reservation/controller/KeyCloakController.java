package hu.cj.reservation.controller;

import hu.cj.reservation.services.KeyCloakService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeyCloakController {
    private KeyCloakService keyCloakService;

    public KeyCloakController(KeyCloakService keyCloakService) {
        this.keyCloakService = keyCloakService;
    }

    @GetMapping(path = "/login/getToken")
    public String getJwtToken(@RequestParam String username, String password){
        return keyCloakService.getToken(username,password);
    }
}
