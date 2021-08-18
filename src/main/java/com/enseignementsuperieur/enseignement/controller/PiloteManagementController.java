package com.enseignementsuperieur.enseignement.controller;


import com.enseignementsuperieur.enseignement.Models.Pilote;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
