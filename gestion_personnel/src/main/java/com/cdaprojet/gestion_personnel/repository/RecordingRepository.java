package com.cdaprojet.gestion_personnel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdaprojet.gestion_personnel.model.recording.Recording;

@Repository
public interface RecordingRepository extends JpaRepository<Recording,Long> {
    
    List<Recording> findAllByEmployeeId(long id);

}
