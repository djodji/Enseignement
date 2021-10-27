package com.enseignementsuperieur.enseignement.controller;


import com.enseignementsuperieur.enseignement.Models.Pilote;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @file: PiloteManagementController.java
 * @brief: implémentation du controller permmettant l'acces de la directrice.
 */
@RestController
@RequestMapping("directrice/api/pilotes")
public class PiloteManagementController {

    private static final List<Pilote> PILOTES= Arrays.asList
            (new Pilote(1,"James", "Lavie","SOCRATE","ABD232","lavie"),
                    new Pilote(2,"Sonia","Brochu","GDEU","TER232","sonia"),
                    new Pilote(3,"Eyram","Kpetsu","SIFU","AGE1234","kpetsu"));

    /**
     * methode permettant l'affichage de la liste des pilotes
     * @return la liste de tous les pilotes
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN_PESD', 'ROLE_DIRECTRICE_PESD')")
    public static List<Pilote> getAllSPilotes() {
        return PILOTES;
    }

    /**
     * methode permettant d'enregistrer un nouveau pilote.
     * @param pilote l'objet pilote instancié.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('pilote:enregistrerUnPilote')")
    public void registerNewPilote(@RequestBody Pilote pilote)
    {
        System.out.println(pilote);
    }

    /**
     *  methode permettant de supprimer les droit a un pilote.
     * @param piloteId
     */
    @DeleteMapping(path = "{piloteId}")
    @PreAuthorize("hasAuthority('pilote:enregistrerUnPilote')")
    public void deletePilote(@PathVariable("piloteId") Integer piloteId)
    {
        System.out.println(piloteId);
    }

    /**
     * pour faire une mise a jour des information concernant un pilote
     * @param piloteId
     * @param pilote
     */
    @PutMapping(path = "{piloteId}")
    @PreAuthorize("hasAuthority('pilote:enregistrerUnPilote')")
    public void updatePilote(@PathVariable("piloteId") Integer piloteId,@RequestBody Pilote pilote)
    {
        System.out.println(String.format("%s %s",piloteId,pilote));
    }


}
