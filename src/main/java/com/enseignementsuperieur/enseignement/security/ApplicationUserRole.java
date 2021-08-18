package com.enseignementsuperieur.enseignement.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.enseignementsuperieur.enseignement.security.ApplicationUserPermissions.*;

/**
 * @file: applicationUserRole.java
 * @brief: définitions des roles existants dans l'application.
 * @Role: PILOTE le pilote d'un système,le pilote aura certaine permission
 * @Role: ADMIN_PESD c'est le pilote administrateur du systeme, il possede tous les droits
 */
public enum ApplicationUserRole {
    PILOTE(Sets.newHashSet()),
    ADMIN_PESD(Sets.newHashSet(PILOTE_READ,PILOTE_WRITE,PUBLICATION_READ,PUBLICATION_WRITE)),
    DIRECTRICE_PESD(Sets.newHashSet(PILOTE_READ,PUBLICATION_READ));

    private final Set<ApplicationUserPermissions> permissions;

    ApplicationUserRole(Set<ApplicationUserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermissions> getPermissions() {
        return permissions;
    }
}
