package com.cdaprojet.gestion_personnel.model.recordingClose;

import java.time.Duration;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecordingCloseDto {
    
    private long id;
    private LocalDate date;
    private boolean newEmployee;
    private Duration totalHours;
    private Duration extraHours;
    private Duration dueHours;
    private long employeeId;

    public RecordingCloseDto(RecordingClose recordingClose) {
        this.id = recordingClose.getId();
        this.date = recordingClose.getDate();
        this.newEmployee = recordingClose.isNewEmployee();
        this.totalHours = recordingClose.getTotalHours();
        this.extraHours = recordingClose.getExtraHours();
        this.dueHours = recordingClose.getDueHours();
        this.employeeId = recordingClose.getEmployee().getId();
    }
}
