package com.enseignementsuperieur.enseignement.controller;

import com.enseignementsuperieur.enseignement.Models.Pilote;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * @file: piloteController.java
 * @brief: implémentation du controlleur de la classe pilote. de cette classe va dériver les pilote
 * admin et les pilotes système.
 */
@RestController
@RequestMapping("api/pilotes")
public class PiloteController {

    private static final List<Pilote> PILOTES= Arrays.asList
            (new Pilote(1,"James", "Lavie","SOCRATE","ABD232","lavie"),
                    new Pilote(2,"Sonia","Brochu","GDEU","TER232","sonia"),
                    new Pilote(3,"Eyram","Kpetsu","SIFU","AGE1234","kpetsu"));


    @GetMapping(path = "{piloteId}")
    public Pilote getPilote(@PathVariable("piloteId") Integer piloteId)
    {
        return PILOTES.stream()
                .filter(pilote -> piloteId.equals(pilote.getPiloteId()))
                .findFirst()
                .orElseThrow(()->new IllegalStateException("Ce pilote dont l'identifiant unique est: "+piloteId+" n'existe pas"));
    }
}
