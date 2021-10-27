package com.enseignementsuperieur.enseignement.TemplateController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
@RequestMapping("/")
public class TemplateController {
    /**
     * retourne la page de connection. l'utilisateurs saisie les informations pour
     * pour se connecter a la bonne page,
     * @return
     */
    @GetMapping("login")
    public  String getLoginView()
    {
        return "login";
    }

    @GetMapping("publications")
    public  String getPublicationsView()
    {
        return "publications";
    }
}
