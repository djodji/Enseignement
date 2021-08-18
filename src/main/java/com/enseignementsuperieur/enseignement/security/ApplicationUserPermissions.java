package com.enseignementsuperieur.enseignement.security;

/**
 * @file: applicationUserPermission.java
 * @brief: énumeration des differente permission qu'on peut donner a un pilote utilisateur de l'applicartion
 * @persmission PILOTE_READ est la permission donné a l'administrateur de lire le contenu de
 * publié par un pilote, connaitre le système auquel appartient le pilote.
 * @permission PILOTE_WRITE est la permission donné a l'administrateur d'enregistrer un nouveau pilote et lui donné
 * les acces ou encore permission.
 * @Permission PUBLICATION_READ L'administrateur a la permission de lire le contenu publié par les pilotes.
 * @permission PUBLICATION_WRITE l'administrateur a pa permission de faire des publications.
 */
public enum ApplicationUserPermissions {
     PILOTE_READ("pilote:lireContenuDuPilote"),
    PILOTE_WRITE("pilote:enregistrerUnPilote"),
    PUBLICATION_READ("publication:lireLesPublications"),
    PUBLICATION_WRITE("publication:ecrireDesPublications");

    private final String permission;

    /**
     * consteur de la classe applicationUserPermission
     * @param permission est la permission a laquelle a droit un utilisateur-Pilote
     */
    ApplicationUserPermissions(String permission) {
        this.permission = permission;
    }

    /**
     * l'asccesseur de la classe.
     * @return
     */
    public String getPermission() {
        return permission;
    }
}
