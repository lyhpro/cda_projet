package com.cdaprojet.gestion_personnel.service.recording;

import java.util.List;

import com.cdaprojet.gestion_personnel.model.recording.RecordingDto;

public interface RecordingService {
    
    void create(RecordingDto recordingDto);
    List<RecordingDto> getAllEmployeeRecording(long employeeId, long yearId, long monthId);

}
