import { Time } from "@angular/common";

export class Recording {
    id: number;
    date: Date;
    hourStart: Time;
    hourStop: Time; 
    breakStart: Time;
    breakStop: Time;
    totalHours: Time; 
    extraHours: Time; 
    dueHours: Time; 
    employeeId: number;
    dayTypeId: number;

    constructor(
        id: number,
        date: Date,
        hourStart: Time,
        hourStop: Time,
        breakStart: Time,
        breakStop: Time,
        totalHours: Time,
        extraHours: Time,
        dueHours: Time,
        employeeId: number,
        dayTypeId: number
    ) {
        this.id = id;
        this.date = date;
        this.hourStart = hourStart;
        this.hourStop = hourStop;
        this.breakStart = breakStart;
        this.breakStop = breakStop;
        this.totalHours = totalHours;
        this.extraHours = extraHours;
        this.dueHours = dueHours;
        this.employeeId = employeeId;
        this.dayTypeId = dayTypeId;
    }
}
