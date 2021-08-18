package com.enseignementsuperieur.enseignement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @file: enseignementSecurityConfig.java
 * @brief: implémentation de la logique de sécuriter.
 */
@Configuration
@EnableWebSecurity
public class EnseignementSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * @brief: configuration basique pour une authentification utilisant
     * le browser.
     * @param httpSecurity les parametre de connexion
     * @throws Exception
     */

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EnseignementSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        /**
         * @brief: methode permettant au pilote d'avoir acces uniqument la page d'acceuil sans
         * avoir un droit de lecture et d'écriture sur les autres pilotes.
         * api** permet de prendre tout le contenu qui vient apres et l'attribué comme
         * droit a un utilisateur.
         * cependant on voudrait qu'un utilisateur-pilote ait le droit de lecture sur son contenu a lui
         */
        httpSecurity.authorizeRequests()
                .antMatchers("/", "index","css/*","/js/*").permitAll()
                .antMatchers("/api/**").hasRole(ApplicationUserRole.PILOTE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    /**
     * methode permettant l'implémentation dans la base de données les pilotes avec leur roles.
     * @return
     */
    @Override
    @Bean
    protected UserDetailsService userDetailsService()
    {
        /**
         * définition du role pilote, c'est a dire le pilote du système
         */
        UserDetails eyramKpetsuUser = User.builder()
                .username("eyram")
                .password(passwordEncoder.encode("kpetsu"))
                .roles(ApplicationUserRole.PILOTE.name()) // ROLE_PILOTE
                .build();

        /**
         * definition du role du pilote-psed, c'est a dire l'administrateur du système.
         */
       UserDetails francoisUser = User.builder()
                .username("francois")
                .password(passwordEncoder.encode("morin"))
                .roles(ApplicationUserRole.ADMIN_PESD.name()) //ROLE DU PILOTE D'ADMINISTRATEUR SYSTÈME
                .build();


        /**
         * definition du role d'un adminitrateur, qui possède un droit de regard
         * sur tout le système mais en lecture uniquement.
         * la directrice pour lire tous les contenus publié et demander des modification, elle peut lire
         * egalement les informations concernants les pilotes qui publient des information.
         */
        UserDetails marieClaudeUser = User.builder()
                .username("marieClaude")
                .password(passwordEncoder.encode("marie123"))
                .roles(ApplicationUserRole.DIRECTRICE_PESD.name()) //ROLE DE LA DIRECTRICE
                .build();

        return new InMemoryUserDetailsManager(eyramKpetsuUser,francoisUser,marieClaudeUser);

    }


}
