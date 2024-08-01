package com.cdaprojet.gestion_personnel.dataload;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.cdaprojet.gestion_personnel.model.contractType.ContractType;
import com.cdaprojet.gestion_personnel.model.dayType.DayType;
import com.cdaprojet.gestion_personnel.model.department.Department;
import com.cdaprojet.gestion_personnel.model.employeeModel.professionalDetail.ProfessionalDetail;
import com.cdaprojet.gestion_personnel.model.hoursPerWeek.HoursPerWeek;
import com.cdaprojet.gestion_personnel.model.pathModel.path.Path;
import com.cdaprojet.gestion_personnel.model.pathModel.pathAssigned.PathAssigned;
import com.cdaprojet.gestion_personnel.model.recording.Recording;
import com.cdaprojet.gestion_personnel.model.role.Role;
import com.cdaprojet.gestion_personnel.model.time.month.Month;
import com.cdaprojet.gestion_personnel.model.time.year.Year;
import com.cdaprojet.gestion_personnel.model.user.User;
import com.cdaprojet.gestion_personnel.repository.ContractTypeRepository;
import com.cdaprojet.gestion_personnel.repository.DayTypeRepository;
import com.cdaprojet.gestion_personnel.repository.DepartmentRepository;
import com.cdaprojet.gestion_personnel.repository.HoursPerWeekRepository;
import com.cdaprojet.gestion_personnel.repository.MonthRepository;
import com.cdaprojet.gestion_personnel.repository.PathAssignedRepository;
import com.cdaprojet.gestion_personnel.repository.PathRepository;
import com.cdaprojet.gestion_personnel.repository.RoleRepository;
import com.cdaprojet.gestion_personnel.repository.UserRepository;
import com.cdaprojet.gestion_personnel.repository.YearRepository;

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

    @Autowired
    private HoursPerWeekRepository hoursPerWeekRepository;

    @Autowired
    private PathRepository pathRepository;

    @Autowired
    private PathAssignedRepository pathAssignedRepository;

    @Autowired
    private MonthRepository monthRepository;

    @Autowired
    private YearRepository yearRepository;

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
        List<User> adminUsers = userRepository.findAllByRoleId(1);
        if(adminUsers.size() == 0) {
            String pwd = new BCryptPasswordEncoder().encode("admin");
            Role role = roleRepository.findByName("ADMIN");
            
            User newAdmin = new User("admin","admin","admin@monmail.com",pwd,role,true,true);
            
            userRepository.save(newAdmin);
        }

        // Crée un User avec le Role USER s'il n'y a aucun User avec le Role USER
        List<User> users = userRepository.findAllByRoleId(2);
        if(users.size() == 0) {
            String pwd = new BCryptPasswordEncoder().encode("user");
            Role role = roleRepository.findByName("USER");

            User newUser = new User("user","user","user@monmail.com",pwd,role,true,true);

            userRepository.save(newUser);
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

        List<HoursPerWeek> hoursPerWeeks = hoursPerWeekRepository.findAll();
        if(hoursPerWeeks.size() == 0) {
            List<HoursPerWeek> newHoursPerWeeks = new ArrayList<HoursPerWeek>();

            HoursPerWeek hoursPerWeekA = new HoursPerWeek(0, 35, true, new ArrayList<ProfessionalDetail>());
            newHoursPerWeeks.add(hoursPerWeekA);

            HoursPerWeek hoursPerWeekB = new HoursPerWeek(0, 30, true, new ArrayList<ProfessionalDetail>());
            newHoursPerWeeks.add(hoursPerWeekB);

            hoursPerWeekRepository.saveAll(newHoursPerWeeks);
        }

        List<Year> years = yearRepository.findAll();
        if(years.size() == 0) {
            List<Year> newYears = new ArrayList<Year>();

            int actualYear = LocalDate.now().getYear();

            for(int year = actualYear ; year < actualYear + 10 ; year++) {
                Year newYear = new Year(0,year);
                newYears.add(newYear);
            }

            yearRepository.saveAll(newYears);
        }

        List<Month> months = monthRepository.findAll();
        if(months.size() == 0) {
            List<Month> newMonths = new ArrayList<Month>();

            Month january = new Month(0,"Janvier",1);
            newMonths.add(january);

            Month february = new Month(0,"Fevrier",2);
            newMonths.add(february);

            Month march = new Month(0,"Mars",3);
            newMonths.add(march);

            Month april = new Month(0,"Avril",4);
            newMonths.add(april);

            Month may = new Month(0,"Mai",5);
            newMonths.add(may);

            Month june = new Month(0,"Juin",6);
            newMonths.add(june);

            Month july = new Month(0,"Juillet",7);
            newMonths.add(july);

            Month august = new Month(0,"Aout",8);
            newMonths.add(august);

            Month september = new Month(0,"Septembre",9);
            newMonths.add(september);

            Month october = new Month(0,"Octobre",10);
            newMonths.add(october);

            Month november = new Month(0,"Novembre",11);
            newMonths.add(november);

            Month december = new Month(0,"Decembre",12);
            newMonths.add(december);

            monthRepository.saveAll(newMonths);

        }

        List<Path> paths = pathRepository.findAll();
        if(paths.size() == 0) {
            List<Path> newPaths = new ArrayList<Path>();

            Path pathA = new Path("Liste des utilisateurs", "home/user/list", "user-list");
            newPaths.add(pathA);

            Path pathB = new Path("Liste des employes","home/employee/list","employee-list");
            newPaths.add(pathB);

            Path pathC = new Path("Ajouter des heures","home/employee/add-hours","add-hours");
            newPaths.add(pathC);

            pathRepository.saveAll(newPaths);
        }

        List<PathAssigned> pathAssigneds = pathAssignedRepository.findAll();
        if(pathAssigneds.size() == 0) {
            List<PathAssigned> newPathAssigneds = new ArrayList<PathAssigned>();

            PathAssigned pathAssignedA = new PathAssigned(pathRepository.findByAlias("user-list"), roleRepository.findByName("ADMIN"));
            newPathAssigneds.add(pathAssignedA);

            PathAssigned pathAssignedB = new PathAssigned(pathRepository.findByAlias("employee-list"), roleRepository.findByName("USER"));
            newPathAssigneds.add(pathAssignedB);

            PathAssigned pathAssignedC = new PathAssigned(pathRepository.findByAlias("add-hours"), roleRepository.findByName("USER"));
            newPathAssigneds.add(pathAssignedC);

            pathAssignedRepository.saveAll(newPathAssigneds);
        }

    }
    
}
