package com.enseignementsuperieur.enseignement.Authentifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.thymeleaf.expression.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.enseignementsuperieur.enseignement.security.ApplicationUserRole.*;

@Repository("Test")
public class EnseignementUserDaoService implements EnseignementUserDao{

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public EnseignementUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<EnseignementUserModel> selectEnseignementUserByUsername(String username) {
        return getEnseignementUsers()
                .stream()
                .filter(enseignementUserModel -> username.equals(enseignementUserModel.getUsername()))
                .findFirst();
    }

    private List<EnseignementUserModel> getEnseignementUsers()
    {
        List<EnseignementUserModel> enseignementUserModels = Arrays.asList(
                new EnseignementUserModel("eyram", passwordEncoder.encode("kpetsu"),
                        PILOTE.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true),

                new EnseignementUserModel("francois", passwordEncoder.encode("morin"),
                        ADMIN_PESD.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true),

                new EnseignementUserModel("marieClaude", passwordEncoder.encode("marie123"),
                        DIRECTRICE_PESD.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true)

        );

        return enseignementUserModels;
    }
}
