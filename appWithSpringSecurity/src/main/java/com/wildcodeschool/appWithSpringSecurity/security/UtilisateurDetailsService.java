package com.wildcodeschool.appWithSpringSecurity.security;

import com.wildcodeschool.appWithSpringSecurity.entity.Utilisateur;
import com.wildcodeschool.appWithSpringSecurity.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UtilisateurDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurDetailsService(@Autowired UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utilisateur> utilisateurInDB = this.utilisateurRepository.findByUsername(username);
        if (utilisateurInDB.isPresent()) {
            Utilisateur utilisateur = utilisateurInDB.get();
            Set<GrantedAuthority> authorities = utilisateur.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("" + role.getName()))
                    .collect(Collectors.toSet());

            return User
                    .withUsername(utilisateur.getUsername())
                    .password(utilisateur.getPassword())
                    .authorities(authorities)
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
