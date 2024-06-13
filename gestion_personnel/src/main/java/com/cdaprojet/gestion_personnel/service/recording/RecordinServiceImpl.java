package com.cdaprojet.gestion_personnel.service.recording;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.dayType.DayType;
import com.cdaprojet.gestion_personnel.model.employee.Employee;
import com.cdaprojet.gestion_personnel.model.recording.Recording;
import com.cdaprojet.gestion_personnel.model.recording.RecordingDto;
import com.cdaprojet.gestion_personnel.repository.DayTypeRepository;
import com.cdaprojet.gestion_personnel.repository.EmployeeRepository;
import com.cdaprojet.gestion_personnel.repository.RecordingRepository;

@Service
public class RecordinServiceImpl implements RecordingService {
    
    @Autowired
    private RecordingRepository recordingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DayTypeRepository dayTypeRepository;

    @Override
    public RecordingDto create(RecordingDto recordingDto) {

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
            employee, 
            dayType
        );

        recordingRepository.save(newRecording);

        return new RecordingDto(newRecording);

    }

    @Override
    public List<RecordingDto> getRecordingsByEmployeeId(long id) {
        
        List<Recording> recordings = recordingRepository.findAllByEmployeeId(id);
        List<RecordingDto> recordingDtos = recordings.stream().map(RecordingDto::new).toList();
        return recordingDtos;
    }

}
