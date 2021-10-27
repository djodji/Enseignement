package com.enseignementsuperieur.enseignement.Authentifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class EnseignementUserService implements UserDetailsService {

    private final EnseignementUserDao enseignementUserDao;
    @Autowired
    public EnseignementUserService(@Qualifier("Test") EnseignementUserDao enseignementUserDao) {
        this.enseignementUserDao = enseignementUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return enseignementUserDao.selectEnseignementUserByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException(String.format("Le nom utilisateur %s n'existe pas"
                ,username)));
    }
}
