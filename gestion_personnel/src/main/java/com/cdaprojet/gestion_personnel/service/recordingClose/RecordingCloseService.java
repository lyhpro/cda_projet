package com.cdaprojet.gestion_personnel.service.recordingClose;

import com.cdaprojet.gestion_personnel.model.employee.Employee;
import com.cdaprojet.gestion_personnel.model.recordingClose.RecordingClose;

public interface RecordingCloseService {
    
    RecordingClose create(Employee employee);

}
