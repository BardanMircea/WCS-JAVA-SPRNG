package com.wildcodeschool.appWithSpringSecurity.service;

import com.wildcodeschool.appWithSpringSecurity.entity.Role;
import com.wildcodeschool.appWithSpringSecurity.entity.Utilisateur;
import com.wildcodeschool.appWithSpringSecurity.repository.RoleRepository;
import com.wildcodeschool.appWithSpringSecurity.repository.UtilisateurRepository;
import com.wildcodeschool.appWithSpringSecurity.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    private final RoleRepository roleRepository;

    private final JwtService jwtService;

    private final BCryptPasswordEncoder encoder;

    public UtilisateurService(@Autowired JwtService jwtService,
                              BCryptPasswordEncoder encoder,
                              UtilisateurRepository utilisateurRepository,
                              RoleRepository roleRepository){
        this.jwtService = jwtService;
        this.encoder = encoder;
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
    }


    public Optional<Utilisateur> findByUsername(String username){
        return this.utilisateurRepository.findByUsername(username);
    }

    public boolean canSignupUser(Utilisateur utilisateur){
        Optional<Utilisateur> utilisateurInDB = this.findByUsername(utilisateur.getUsername());
        if (utilisateurInDB.isEmpty()){
            Utilisateur utilisateurToSave = new Utilisateur();
            utilisateurToSave.setUsername(utilisateur.getUsername());
            utilisateurToSave.setPassword(encoder.encode(utilisateur.getPassword()));

            for (Role roleFromModel : utilisateur.getRoles()) {
                Optional<Role> roleFromDB = this.roleRepository.findByName(roleFromModel.getName());
                roleFromDB.ifPresent(role -> utilisateurToSave.getRoles().add(role));
            }
            this.utilisateurRepository.save(utilisateurToSave);

            return true;
        }
        return false;
    }

    public Optional<String> loginUser(Utilisateur utilisateur) {
        Optional<Utilisateur> utilisateurInDB = this.findByUsername(utilisateur.getUsername());

        if (utilisateurInDB.isPresent() && encoder.matches(utilisateur.getPassword(), utilisateurInDB.get().getPassword())){
            String token = this.jwtService.generateToken(utilisateurInDB.get().getUsername(), utilisateurInDB.get().getRoles());
            return Optional.of(token);
        }

        return Optional.empty();
       }
}