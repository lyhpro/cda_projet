package com.cdaprojet.gestion_personnel.model.recording;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecordingDto {
    
    private long id;
    private LocalDate date;
    private LocalDate dateStart;
    private LocalDate dateStop;
    private LocalTime hourStart;
    private LocalTime hourStop;
    private LocalTime breakStart;
    private LocalTime breakStop;
    private Duration extraHours;
    private Duration dueHours;
    private Duration totalHours;
    private long employeeId;
    private long dayTypeId;

    public RecordingDto(Recording recording) {
        this.id = recording.getId();
        this.date = recording.getDate();
        this.hourStart = recording.getHourStart();
        this.hourStop = recording.getHourStop();
        this.breakStart = recording.getBreakStart();
        this.breakStop = recording.getBreakStop();
        this.extraHours = recording.getExtraHours();
        this.dueHours = recording.getDueHours();
        this.totalHours = recording.getTotalHours();
        this.employeeId = recording.getEmployee().getId();
        this.dayTypeId = recording.getDayType().getId();
    }
}
