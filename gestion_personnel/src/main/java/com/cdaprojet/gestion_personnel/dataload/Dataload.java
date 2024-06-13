package com.cdaprojet.gestion_personnel.dataload;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.cdaprojet.gestion_personnel.model.contractType.ContractType;
import com.cdaprojet.gestion_personnel.model.dayType.DayType;
import com.cdaprojet.gestion_personnel.model.department.Department;
import com.cdaprojet.gestion_personnel.model.professionalDetail.ProfessionalDetail;
import com.cdaprojet.gestion_personnel.model.recording.Recording;
import com.cdaprojet.gestion_personnel.model.role.Role;
import com.cdaprojet.gestion_personnel.model.user.User;
import com.cdaprojet.gestion_personnel.repository.ContractTypeRepository;
import com.cdaprojet.gestion_personnel.repository.DayTypeRepository;
import com.cdaprojet.gestion_personnel.repository.DepartmentRepository;
import com.cdaprojet.gestion_personnel.repository.RoleRepository;
import com.cdaprojet.gestion_personnel.repository.UserRepository;

@Component
public class Dataload implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ContractTypeRepository contractTypeRepository;

    @Autowired
    private DayTypeRepository dayTypeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

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

        List<ContractType> contractTypes = contractTypeRepository.findAll();
        if(contractTypes.size() == 0) {
            List<ContractType> newContractTypes = new ArrayList<ContractType>();

            ContractType contractTypeA = new ContractType(0,"CDI",true,new ArrayList<ProfessionalDetail>());
            newContractTypes.add(contractTypeA);

            ContractType contractTypeB = new ContractType(0,"CDD",true,new ArrayList<ProfessionalDetail>());
            newContractTypes.add(contractTypeB);

            contractTypeRepository.saveAll(newContractTypes);
        }

        List<DayType> dayTypes = dayTypeRepository.findAll();
        if(dayTypes.size() == 0) {
            List<DayType> newDayTypes = new ArrayList<DayType>();

            DayType dayTypeA = new DayType(0, "travail", true, new ArrayList<Recording>());
            newDayTypes.add(dayTypeA);

            DayType dayTypeB = new DayType(0, "vacance", true, new ArrayList<Recording>());
            newDayTypes.add(dayTypeB);

            DayType dayTypeC = new DayType(0, "rtt", true, new ArrayList<Recording>());
            newDayTypes.add(dayTypeC);

            DayType dayTypeD = new DayType(0, "maladie", true, new ArrayList<Recording>());
            newDayTypes.add(dayTypeD);

            dayTypeRepository.saveAll(newDayTypes);
        }

        List<Department> departments = departmentRepository.findAll();
        if(departments.size() == 0) {
            List<Department> newDepartments = new ArrayList<Department>();

            Department departmentA = new Department(0, "DRH", "Departement des Ressources Humaines", true, new ArrayList<ProfessionalDetail>());
            newDepartments.add(departmentA);

            Department departmentB = new Department(0, "DI", "Departement Informatique", true, new ArrayList<ProfessionalDetail>());
            newDepartments.add(departmentB);

            departmentRepository.saveAll(newDepartments);
        }

    }
    
}
