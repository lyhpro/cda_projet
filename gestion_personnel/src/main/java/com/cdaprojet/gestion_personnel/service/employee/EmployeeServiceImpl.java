package com.cdaprojet.gestion_personnel.service.employee;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.contractType.ContractType;
import com.cdaprojet.gestion_personnel.model.department.Department;
import com.cdaprojet.gestion_personnel.model.employeeModel.contactDetail.ContactDetail;
import com.cdaprojet.gestion_personnel.model.employeeModel.employee.Employee;
import com.cdaprojet.gestion_personnel.model.employeeModel.employee.EmployeeDto;
import com.cdaprojet.gestion_personnel.model.employeeModel.employee.EmployeeForm;
import com.cdaprojet.gestion_personnel.model.employeeModel.professionalDetail.ProfessionalDetail;
import com.cdaprojet.gestion_personnel.model.hoursPerWeek.HoursPerWeek;
import com.cdaprojet.gestion_personnel.model.recording.Recording;
import com.cdaprojet.gestion_personnel.model.specialDay.holiday.Holiday;
import com.cdaprojet.gestion_personnel.model.specialDay.illness.Illness;
import com.cdaprojet.gestion_personnel.model.specialDay.rtt.Rtt;
import com.cdaprojet.gestion_personnel.model.time.month.Month;
import com.cdaprojet.gestion_personnel.model.time.year.Year;
import com.cdaprojet.gestion_personnel.repository.ContractTypeRepository;
import com.cdaprojet.gestion_personnel.repository.DepartmentRepository;
import com.cdaprojet.gestion_personnel.repository.EmployeeRepository;
import com.cdaprojet.gestion_personnel.repository.HolidayRepository;
import com.cdaprojet.gestion_personnel.repository.HoursPerWeekRepository;
import com.cdaprojet.gestion_personnel.repository.MonthRepository;
import com.cdaprojet.gestion_personnel.repository.RttRepository;
import com.cdaprojet.gestion_personnel.repository.YearRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ContractTypeRepository contractTypeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HoursPerWeekRepository hoursPerWeekRepository;

    @Autowired
    private YearRepository yearRepository;

    @Autowired
    private MonthRepository monthRepository;

    @Autowired
    private HolidayRepository holidayRepository;

    @Autowired
    private RttRepository rttRepository;

    @Override
    public EmployeeDto create(EmployeeForm employeeForm) {

        ContactDetail newContactDetail = new ContactDetail(
            0,
            employeeForm.getEmail(),
            employeeForm.getAddress(),
            employeeForm.getPostalCode(),
            employeeForm.getCity(),
            employeeForm.getHomenumber(),
            employeeForm.getPhonenumber()
        );

        Department department = departmentRepository.findById(employeeForm.getDepartmentId()).orElse(null);
        ContractType contractType = contractTypeRepository.findById(employeeForm.getContractId()).orElse(null);
        HoursPerWeek hoursPerWeek = hoursPerWeekRepository.findById(employeeForm.getHoursPerWeekId()).orElse(null);

        ProfessionalDetail newProfessionalDetail = new ProfessionalDetail(
            0,
            employeeForm.getPost(),
            employeeForm.getDateOfHiring(),
            employeeForm.getDateEndOfContract(),
            employeeForm.getSalary(),
            hoursPerWeek,
            contractType,
            department
        );

        Employee newEmployee = new Employee(
            0, 
            employeeForm.getSecondname(), 
            employeeForm.getFirstname(), 
            employeeForm.getPlaceOfBirth(), 
            employeeForm.getDateOfBirth(),
            true, 
            LocalDate.now(),
            newContactDetail, 
            newProfessionalDetail,
            new ArrayList<Holiday>(),
            new ArrayList<Rtt>(),
            new ArrayList<Illness>(),
            new ArrayList<Recording>()
        );

        employeeRepository.save(newEmployee);

        return new EmployeeDto(newEmployee);
    }

    @Override
    public EmployeeDto update(EmployeeForm employeeForm) {
        Employee oldEmployee = employeeRepository.findById(employeeForm.getId()).orElse(null);
        
        ContactDetail oldContactDetail = oldEmployee.getContactDetail();
        ContactDetail updatedContactDetail = new ContactDetail(
            oldContactDetail.getId(), 
            employeeForm.getEmail(), 
            employeeForm.getAddress(), 
            employeeForm.getPostalCode(), 
            employeeForm.getCity(), 
            employeeForm.getHomenumber(), 
            employeeForm.getPhonenumber()
        );

        ContractType newContractType = contractTypeRepository.findById(employeeForm.getContractId()).orElse(null);
        Department newDepartment = departmentRepository.findById(employeeForm.getDepartmentId()).orElse(null);
        HoursPerWeek hoursPerWeek = hoursPerWeekRepository.findById(employeeForm.getHoursPerWeekId()).orElse(null);

        ProfessionalDetail oldProfessionalDetail = oldEmployee.getProfessionalDetail();
        ProfessionalDetail updateProfessionalDetail = new ProfessionalDetail(
            oldProfessionalDetail.getId(), 
            employeeForm.getPost(), 
            employeeForm.getDateOfHiring(), 
            employeeForm.getDateEndOfContract(), 
            employeeForm.getSalary(), 
            hoursPerWeek, 
            newContractType, 
            newDepartment
        );
        
        Employee updatedEmployee = new Employee(
            employeeForm.getId(), 
            employeeForm.getSecondname(), 
            employeeForm.getFirstname(), 
            employeeForm.getPlaceOfBirth(), 
            employeeForm.getDateOfBirth(), 
            oldEmployee.isEnable(), 
            LocalDate.now(), 
            updatedContactDetail, 
            updateProfessionalDetail,
            oldEmployee.getHolidays(),
            oldEmployee.getRtts(),
            oldEmployee.getIllnesses(),
            oldEmployee.getRecordings()
        );

        employeeRepository.save(updatedEmployee);

        return new EmployeeDto(updatedEmployee);
    }

    @Override
    public EmployeeDto delete(long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        employee.setEnable(false);
        employeeRepository.save(employee);
        return new EmployeeDto(employee);
    }

    @Override
    public EmployeeDto getEmployeeById(long id) {

        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee != null) {
            return new EmployeeDto(employee);
        }
        return null;

    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> allEmployee = employeeRepository.findAll();
        List<EmployeeDto> allEmployeeDto = allEmployee.stream().filter(employee -> employee.isEnable()).map(EmployeeDto::new).toList();
        return allEmployeeDto;
    }

    @Override
    public Map<String,List<Integer>> getEmployeeNbHolidaysRttsIllnesses(long employeeId, long yearId, long monthId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        Year year = yearRepository.findById(yearId).orElse(null);
        
        Map<String, List<Integer>> mapEmployeeRttVacanceMaladieDuration = new HashMap<String, List<Integer>>();

        Month month = monthRepository.findById(monthId).orElse(null);

        this.setNbHolidays(mapEmployeeRttVacanceMaladieDuration, employee, month, year);
        this.setNbRtts(mapEmployeeRttVacanceMaladieDuration, employee, month, year);
        this.setNbIllnesses(mapEmployeeRttVacanceMaladieDuration, employee, month, year);

        return mapEmployeeRttVacanceMaladieDuration;
    }

    @Override
    public void addDaysToEmployeeSpecialDay(long employeeId, String dayname, int nbDay) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if(dayname.equals("vacance")) {
            Holiday latestHoliday = employee.getHolidays().stream().max(Comparator.comparing(Holiday::getDate)).orElse(null);
            if(latestHoliday != null) {
                int newNbDays = latestHoliday.getNbDay() + nbDay;
                Holiday newHoliday = new Holiday(0, newNbDays, new Timestamp(System.currentTimeMillis()), employee);
                holidayRepository.save(newHoliday);
            } else {
                holidayRepository.save(new Holiday(0,nbDay,new Timestamp(System.currentTimeMillis()),employee));
            }
        } else if(dayname.equals("rtt")) {
            Rtt latestRtt = employee.getRtts().stream().max(Comparator.comparing(Rtt::getDate)).orElse(null);
            if(latestRtt != null) {
                int newNbDays = latestRtt.getNbDay() + nbDay;
                Rtt newRtt = new Rtt(0, newNbDays, new Timestamp(System.currentTimeMillis()), employee);
                rttRepository.save(newRtt);
            } else {
                rttRepository.save(new Rtt(0, nbDay, new Timestamp(System.currentTimeMillis()), employee));
            }
        }
    }

    @Override
    public Map<String,List<Duration>> getEmployeeWorkingHours(long employeeId, long yearId, long monthId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        Year year = yearRepository.findById(yearId).orElse(null);
        Month month = monthRepository.findById(monthId).orElse(null);
        Map<String,List<Duration>> mapWorkingHours = new HashMap<String, List<Duration>>();
        List<Duration> totalHourDuration = new ArrayList<Duration>();
        List<Duration> totalExtraHourDuration = new ArrayList<Duration>();
        List<Duration> totalDueHourDuration = new ArrayList<Duration>();

        List<Recording> yearRecordings = new ArrayList<Recording>();
        yearRecordings = employee.getRecordings().stream().filter(recording -> recording.getDate().getYear() == year.getValue()).toList();
        
        List<Recording> monthRecordings = new ArrayList<Recording>();
        monthRecordings = yearRecordings.stream().filter(recording -> recording.getDate().getMonth().getValue() == month.getNumber()).toList();
        
        Duration yearTotalHourDuration = Duration.ofHours(0).plusMinutes(0);
        Duration monthTotalHourDuration = Duration.ofHours(0).plusMinutes(0);
        
        Duration yearExtraHourDuration = Duration.ofHours(0).plusMinutes(0);
        Duration monthExtraHourDuration = Duration.ofHours(0).plusMinutes(0);
        
        Duration yearDueHourDuration = Duration.ofHours(0).plusMinutes(0);
        Duration monthDueHourDuration = Duration.ofHours(0).plusMinutes(0);
        
        if(yearRecordings.size() != 0) {
            for(Recording yearRecording: yearRecordings) {
                if (yearRecording.getTotalHours() != null) {
                    yearTotalHourDuration = yearTotalHourDuration.plus(yearRecording.getTotalHours());
                }
                if(yearRecording.getExtraHours() != null) {
                    yearExtraHourDuration = yearExtraHourDuration.plus(yearRecording.getExtraHours());
                }
                if(yearRecording.getDueHours() != null) {
                    yearDueHourDuration = yearDueHourDuration.plus(yearRecording.getDueHours());
                }
            }
            for(Recording monthRecording: monthRecordings) {
                if (monthRecording.getTotalHours() != null) {
                    monthTotalHourDuration = monthTotalHourDuration.plus(monthRecording.getTotalHours());
                }
                if (monthRecording.getExtraHours() != null) {
                    monthExtraHourDuration = monthExtraHourDuration.plus(monthRecording.getExtraHours());
                }
                if (monthRecording.getDueHours() != null) {
                    monthDueHourDuration = monthDueHourDuration.plus(monthRecording.getDueHours());
                }
            }
        }

        totalHourDuration.add(0,yearTotalHourDuration);
        totalHourDuration.add(1,monthTotalHourDuration);

        totalExtraHourDuration.add(0,yearExtraHourDuration);
        totalExtraHourDuration.add(1,monthExtraHourDuration);

        totalDueHourDuration.add(0,yearDueHourDuration);
        totalDueHourDuration.add(1,monthDueHourDuration);

        mapWorkingHours.put("Total heures", totalHourDuration);
        mapWorkingHours.put("Total heures supplementaire", totalExtraHourDuration);
        mapWorkingHours.put("Total heures manquante", totalDueHourDuration);

        return mapWorkingHours;

    }

    public void setNbHolidays(Map<String,List<Integer>> map, Employee employee, Month month, Year year) {
        List<Integer> nbHolidays = new ArrayList<Integer>(); 
        List<Recording> holidayRecordingsOfYear = employee.getRecordings()
            .stream()
            .filter(recording -> recording.getDate().getYear() == year.getValue())
            .filter(recording -> recording.getDayType().getName().equals("vacance"))
            .toList();
        int nbHolidayPerYear = holidayRecordingsOfYear.size();

        int nbHolidayPerYearAndMonth = 0;
        int nbHolidayLeft = 0;
        if(month != null) {
            List<Recording> holidayRecordingsOfYearAndMonth = holidayRecordingsOfYear
                .stream()
                .filter(recording -> recording.getDate().getMonth().getValue() == month.getNumber())
                .toList();
            nbHolidayPerYearAndMonth = holidayRecordingsOfYearAndMonth.size();

            Holiday recentHoliday = employee.getHolidays()
                .stream()
                .filter(holiday -> holiday.getDate().toLocalDateTime().getYear() == year.getValue())
                .filter(holiday -> holiday.getDate().toLocalDateTime().getMonth().getValue() == month.getNumber())
                .max(Comparator.comparing(Holiday::getDate))
                .orElse(null);
            if(recentHoliday != null) {
                nbHolidayLeft = recentHoliday.getNbDay();
            }

        }

        nbHolidays.add(0,nbHolidayPerYear);
        nbHolidays.add(1,nbHolidayPerYearAndMonth);
        nbHolidays.add(2,nbHolidayLeft);
        
        map.put("Vacance", nbHolidays);
    }

    public void setNbRtts(Map<String,List<Integer>> map, Employee employee, Month month, Year year) {
        List<Integer> nbRtts = new ArrayList<Integer>(); 

        List<Recording> rttRecordingsOfYear = employee.getRecordings()
            .stream()
            .filter(recording -> recording.getDate().getYear() == year.getValue())
            .filter(recording -> recording.getDayType().getName().equals("rtt"))
            .toList();
        int nbRttPerYear = rttRecordingsOfYear.size();

        int nbRttPerYearAndMonth = 0;
        int nbRttLeft = 0;
        if(month != null) {
            List<Recording> rttRecordingsOfYearAndMonth = rttRecordingsOfYear
                .stream()
                .filter(recording -> recording.getDate().getMonth().getValue() == month.getNumber())
                .toList();
            nbRttPerYearAndMonth = rttRecordingsOfYearAndMonth.size();

            Rtt recentRtt = employee.getRtts()
                .stream()
                .filter(rtt -> rtt.getDate().toLocalDateTime().getYear() == year.getValue())
                .filter(rtt -> rtt.getDate().toLocalDateTime().getMonth().getValue() == month.getNumber())
                .max(Comparator.comparing(Rtt::getDate))
                .orElse(null);
            if(recentRtt != null) {
                nbRttLeft = recentRtt.getNbDay();
            }
        }

        nbRtts.add(0,nbRttPerYear);
        nbRtts.add(1,nbRttPerYearAndMonth);
        nbRtts.add(2,nbRttLeft);
        
        map.put("Rtt", nbRtts);
    }

    public void setNbIllnesses(Map<String,List<Integer>> map, Employee employee, Month month, Year year) {
        List<Integer> nbIllnesses = new ArrayList<Integer>(); 

        List<Recording> illnessRecordingsOfYear = employee.getRecordings()
            .stream()
            .filter(recording -> recording.getDate().getYear() == year.getValue())
            .filter(recording -> recording.getDayType().getName().equals("maladie"))
            .toList();
        int nbIllnessPerYear = illnessRecordingsOfYear.size();

        int nbIllnessPerYearAndMonth = 0;
        int nbIllnessLeft = 0;
        if(month != null) {
            List<Recording> illnessRecordingsOfYearAndMonth = illnessRecordingsOfYear
                .stream()
                .filter(recording -> recording.getDate().getMonth().getValue() == month.getNumber())
                .toList();
            nbIllnessPerYearAndMonth = illnessRecordingsOfYearAndMonth.size();

            Illness recentIllness = employee.getIllnesses()
                .stream()
                .filter(illness -> illness.getDate().toLocalDateTime().getYear() == year.getValue())
                .filter(illness -> illness.getDate().toLocalDateTime().getMonth().getValue() == month.getNumber())
                .max(Comparator.comparing(Illness::getDate))
                .orElse(null);
            if(recentIllness != null) {
                nbIllnessLeft = recentIllness.getNbDay();
            }
        }

        nbIllnesses.add(0,nbIllnessPerYear);
        nbIllnesses.add(1,nbIllnessPerYearAndMonth);
        nbIllnesses.add(2,nbIllnessLeft);
        
        map.put("Maladie", nbIllnesses);
    }

}
