package com.wildcodeschool.appWithSpringSecurity.service;

import com.wildcodeschool.appWithSpringSecurity.entity.Role;
import com.wildcodeschool.appWithSpringSecurity.entity.Utilisateur;
import com.wildcodeschool.appWithSpringSecurity.enums.RoleEnum;
import com.wildcodeschool.appWithSpringSecurity.repository.RoleRepository;
import com.wildcodeschool.appWithSpringSecurity.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GeneratorService {

    private RoleRepository roleRepository;
    private UtilisateurRepository utilisateurRepository;
    private BCryptPasswordEncoder encoder;

    public GeneratorService(@Autowired RoleRepository roleRepository, @Autowired UtilisateurRepository utilisateurRepository,
                            @Autowired BCryptPasswordEncoder encoder){
        this.roleRepository = roleRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.encoder = encoder;
    }

    public void seedDatabase(){
        seedRoles();
        seedUtilisateurs();
    }

    private void seedRoles(){
        this.roleRepository.save(new Role(RoleEnum.ADMIN));
        this.roleRepository.save(new Role(RoleEnum.USER));
    }

    private void seedUtilisateurs(){
        this.utilisateurRepository.save(new Utilisateur("user1", this.encoder.encode("userOne"),
                Set.of(this.roleRepository.findByName(RoleEnum.USER).get())));
        this.utilisateurRepository.save(new Utilisateur("user2", this.encoder.encode("userTwo"),
                Set.of(this.roleRepository.findByName(RoleEnum.USER).get())));
        this.utilisateurRepository.save(new Utilisateur("userAdmin", this.encoder.encode("userAdmin"),
                Set.of(this.roleRepository.findByName(RoleEnum.USER).get(), this.roleRepository.findByName(RoleEnum.ADMIN).get())));
        this.utilisateurRepository.save(new Utilisateur("admin", this.encoder.encode("admin"),
                Set.of(this.roleRepository.findByName(RoleEnum.ADMIN).get())));
    }
}
