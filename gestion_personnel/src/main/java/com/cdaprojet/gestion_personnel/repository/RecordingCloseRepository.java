package com.cdaprojet.gestion_personnel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdaprojet.gestion_personnel.model.recordingClose.RecordingClose;

@Repository
public interface RecordingCloseRepository extends JpaRepository<RecordingClose,Long> {
    
    List<RecordingClose> findAllByEmployeeId(long employeeId);
    
}
