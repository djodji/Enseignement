package com.enseignementsuperieur.enseignement.security;

import com.enseignementsuperieur.enseignement.Authentifications.EnseignementUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

/**
 * @file: enseignementSecurityConfig.java
 * @brief: implémentation de la logique de sécuriter.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class EnseignementSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * @brief: configuration basique pour une authentification utilisant
     * le browser.
     * @param httpSecurity les parametre de connexion
     * @throws Exception
     */

    private final PasswordEncoder passwordEncoder;
    private final EnseignementUserService enseignementUserService;

    @Autowired
    public EnseignementSecurityConfig(PasswordEncoder passwordEncoder, EnseignementUserService enseignementUserService) {
        this.passwordEncoder = passwordEncoder;
        this.enseignementUserService = enseignementUserService;
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
         * //TODO mieux comprendre cette annotation et chercher des references.
         * tokenValiditySeconds() est utiliser dans le cas des base de données en memoire.
         * tokenValidityRepository() est utiliser dans le cas des bases de données reelle.
         */
        httpSecurity

//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())//permettre a spring de generer les tokens
//                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index","css/*","/js/*").permitAll()
                .antMatchers("/api/**").hasRole(ApplicationUserRole.PILOTE.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/publications",true)
                    .passwordParameter("Password")
                    .usernameParameter("Username")
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)) //pour un enregistrement dans la base de données en memoire pour deux semaines
                    .key("hauteSecurite")
                    .rememberMeParameter("remember-me")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("remember-me","JSESSIONID")
                    .logoutSuccessUrl("/login");


    }

    /**
     *
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Override
    protected void configure (AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
    {
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
    }


    /**
     *
     */

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(enseignementUserService);
        return provider;
    }


}
