package com.cdaprojet.gestion_personnel.service.recording;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.dayType.DayType;
import com.cdaprojet.gestion_personnel.model.employee.Employee;
import com.cdaprojet.gestion_personnel.model.month.Month;
import com.cdaprojet.gestion_personnel.model.professionalDetail.ProfessionalDetail;
import com.cdaprojet.gestion_personnel.model.recording.Recording;
import com.cdaprojet.gestion_personnel.model.recording.RecordingDto;
import com.cdaprojet.gestion_personnel.repository.DayTypeRepository;
import com.cdaprojet.gestion_personnel.repository.EmployeeRepository;
import com.cdaprojet.gestion_personnel.repository.MonthRepository;
import com.cdaprojet.gestion_personnel.repository.RecordingRepository;
import com.cdaprojet.gestion_personnel.service.publicHoliday.PublicHolidayService;

@Service
public class RecordinServiceImpl implements RecordingService {
    
    @Autowired
    private RecordingRepository recordingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DayTypeRepository dayTypeRepository;

    @Autowired
    private PublicHolidayService publicHolidayService;

    @Autowired
    private MonthRepository monthRepository;

    @Override
    public void create(RecordingDto recordingDto) {

        Employee employee = employeeRepository.findById(recordingDto.getEmployeeId()).orElse(null);
        
        DayType dayType = dayTypeRepository.findById(recordingDto.getDayTypeId()).orElse(null);

        Recording newRecording = new Recording(
            0, 
            null,
            null,
            null, 
            null, 
            null, 
            null, 
            null,
            null,
            null,
            null, 
            employee, 
            dayType
        );

        // List<Recording> recordings = new ArrayList<Recording>();

        if (recordingDto.getDayTypeId() == 1) {
            newRecording.setDate(recordingDto.getDate());
            newRecording.setHourStart(recordingDto.getHourStart());
            newRecording.setHourStop(recordingDto.getHourStop());
            newRecording.setBreakStart(recordingDto.getBreakStart());
            newRecording.setBreakStop(recordingDto.getBreakStop());
            this.initTotalHours(recordingDto, newRecording);
            this.initExtraHours(employee, newRecording);
            this.initDueHours(employee, newRecording);
            recordingRepository.save(newRecording);
        } else {
            List<LocalDate> datesBetweenTwoDate = getDatesBetweenTwoDate(recordingDto.getDateStart(), recordingDto.getDateStop());
            for(LocalDate date: datesBetweenTwoDate) {
                Recording recording = new Recording(
                    0, 
                    null,
                    null,
                    null, 
                    null, 
                    null, 
                    null, 
                    null,
                    null,
                    null,
                    null, 
                    employee, 
                    dayType
                );
                recording.setDate(date);
                recording.setDateStart(recordingDto.getDateStart());
                recording.setDateStop(recordingDto.getDateStop());
                recordingRepository.save(recording);
            }
        }

    }

    @Override
    public List<RecordingDto> getAllEmployeeRecording(long employeeId, long year, long monthId) {
        
        Month month = monthRepository.findById(monthId).orElse(null);

        List<Recording> recordings = recordingRepository.findAllByEmployeeId(employeeId);
        List<RecordingDto> recordingDtos = recordings
            .stream()
            .filter(recording -> recording.getDate().getYear() == year)
            .filter(recording -> recording.getDate().getMonth().getValue() == month.getNumber())
            .map(RecordingDto::new)
            .toList();
        return recordingDtos;
    }

    public void initTotalHours(RecordingDto recordingDto, Recording newRecording) {
        Duration hourStartDuration = Duration.ofHours(recordingDto.getHourStart().getHour()).plusMinutes(recordingDto.getHourStart().getMinute());
        Duration hourStopDuration = Duration.ofHours(recordingDto.getHourStop().getHour()).plusMinutes(recordingDto.getHourStop().getMinute());
        Duration hourDuration = hourStopDuration.minus(hourStartDuration);

        Duration breakStartDuration = Duration.ofHours(recordingDto.getBreakStart().getHour()).plusMinutes(recordingDto.getBreakStart().getMinute());
        Duration breakStopDuration = Duration.ofHours(recordingDto.getBreakStop().getHour()).plusMinutes(recordingDto.getBreakStop().getMinute());
        Duration breakDuration = breakStopDuration.minus(breakStartDuration);

        Duration newTotalHours = hourDuration.minus(breakDuration);
        newRecording.setTotalHours(newTotalHours);
    }

    public void initExtraHours(Employee employee, Recording newRecording) {
        ProfessionalDetail employeeProfessionalDetail = employee.getProfessionalDetail();
        long employeeDailyHours = employeeProfessionalDetail.getHoursPerWeek().getHours()/5;
        long employeeTotalHours = newRecording.getTotalHours().toHours();
        if(employeeTotalHours > employeeDailyHours) {
            Duration extraHoursDuration = Duration.ofHours(employeeTotalHours - employeeDailyHours);
            newRecording.setExtraHours(extraHoursDuration);
        }
    }
    
    public void initDueHours(Employee employee, Recording newRecording) {
        ProfessionalDetail employeeProfessionalDetail = employee.getProfessionalDetail();
        long employeeDailyHours = employeeProfessionalDetail.getHoursPerWeek().getHours()/5;
        long employeeTotalHours = newRecording.getTotalHours().toHours();
        if(employeeTotalHours < employeeDailyHours) {
            Duration extraHoursDuration = Duration.ofHours(employeeDailyHours - employeeTotalHours);
            newRecording.setDueHours(extraHoursDuration);
        }
    }

    public List<LocalDate> getDatesBetweenTwoDate(LocalDate firstDate, LocalDate secondDate) {
        List<LocalDate> publicHolidays = publicHolidayService.publicHolidaysOfTheYear(firstDate.getYear());
        return firstDate.datesUntil(secondDate)
        .filter(date -> !publicHolidays.contains(date))
        .filter(date -> date.getDayOfWeek() != DayOfWeek.SATURDAY)
        .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY)
        .toList();
    }

}
