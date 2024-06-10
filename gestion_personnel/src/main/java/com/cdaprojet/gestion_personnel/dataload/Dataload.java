package com.cdaprojet.gestion_personnel.dataload;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.cdaprojet.gestion_personnel.model.role.Role;
import com.cdaprojet.gestion_personnel.model.user.User;
import com.cdaprojet.gestion_personnel.repository.RoleRepository;
import com.cdaprojet.gestion_personnel.repository.UserRepository;

@Component
public class Dataload implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        
        // Crée des Role si il n'y a aucun Role
        List<Role> allRoles = roleRepository.findAll();
        if(allRoles.size() == 0) {
            List<Role> listRoles = new ArrayList<Role>();

            Role admin = new Role("ADMIN","Administrateur système");
            Role user = new Role("USER","Utilisateur");

            listRoles.add(admin);
            listRoles.add(user);

            roleRepository.saveAll(listRoles);
        }

        // Crée un User avec le Role ADMIN s'il n'y a aucun User avec le Role ADMIN
        User adminUser = userRepository.findByRoleId(1);
        if(adminUser == null) {
            String pwd = new BCryptPasswordEncoder().encode("admin");
            Role role = roleRepository.findByName("ADMIN");
            
            User users = new User("admin","admin","admin@monmail.com",pwd,role,true);
            
            userRepository.save(users);
        }

    }
    
}
