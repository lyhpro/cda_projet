package com.cdaprojet.gestion_personnel.service.recordingClose;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.employee.Employee;
import com.cdaprojet.gestion_personnel.model.recordingClose.RecordingClose;
import com.cdaprojet.gestion_personnel.repository.RecordingCloseRepository;

@Service
public class RecordingCloseServiceImpl implements RecordingCloseService {
    
    @Autowired
    private RecordingCloseRepository recordingCloseRepository;

    @Override
    public RecordingClose create(Employee employee) {
        List<RecordingClose> recordingCloses = recordingCloseRepository.findAllByEmployeeId(employee.getId());
        if(recordingCloses.size() == 1) {
            RecordingClose newRecordingClose = new RecordingClose(
                0, 
                LocalDate.now(), 
                true, 
                null, 
                null, 
                null, 
                employee
            );
        } else {
            RecordingClose newRecordingClose = new RecordingClose(
                0, 
                LocalDate.now(), 
                false, 
                null, 
                null, 
                null, 
                employee
            );
        }
        return recordingCloseRepository.save(newRecordingClose);
    }
}
