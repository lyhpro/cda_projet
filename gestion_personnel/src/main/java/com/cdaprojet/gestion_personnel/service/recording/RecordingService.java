package com.cdaprojet.gestion_personnel.service.recording;

import java.util.List;

import com.cdaprojet.gestion_personnel.model.recording.RecordingDto;

public interface RecordingService {
    
    RecordingDto create(RecordingDto recordingDto);
    List<RecordingDto> getRecordingsByEmployeeId(long id);

}
