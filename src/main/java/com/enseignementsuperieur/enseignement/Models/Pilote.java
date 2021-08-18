package com.enseignementsuperieur.enseignement.Models;

/**
 * @file: pilote.java
 * brief: la classe modele qui définit les propriétés des differentes classe.
 */
public class Pilote {
    /**
     * déclaration des propriétés de la classe pilote
     */
    private final Integer piloteId;
    private final String nomPilote;
    private final String prenomPilote;
    private final String nomDuSysteme;
    private final String identifiantSysteme;
    private final String password;

    public Pilote(Integer piloteId,
                  String nomPilote,
                  String prenomPilote,
                  String nomDuSysteme,
                  String identifiantSysteme,
                  String password) {
        this.piloteId = piloteId;
        this.nomPilote = nomPilote;
        this.prenomPilote = prenomPilote;
        this.nomDuSysteme = nomDuSysteme;
        this.identifiantSysteme = identifiantSysteme;
        this.password = password;
    }

    public Integer getPiloteId() {
        return piloteId;
    }

    public String getNomPilote() {
        return nomPilote;
    }

    public String getPrenomPilote() {
        return prenomPilote;
    }

    public String getNomDuSysteme() {
        return nomDuSysteme;
    }

    public String getIdentifiantSysteme() {
        return identifiantSysteme;
    }

    public String getPassword() {
        return password;
    }
}
