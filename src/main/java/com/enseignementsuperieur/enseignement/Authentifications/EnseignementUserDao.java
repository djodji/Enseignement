package com.enseignementsuperieur.enseignement.Authentifications;

import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface EnseignementUserDao {

    public Optional<EnseignementUserModel> selectEnseignementUserByUsername(String username);
}
