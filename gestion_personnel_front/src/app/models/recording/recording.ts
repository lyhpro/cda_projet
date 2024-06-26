import { Time } from "@angular/common";

export class Recording {
    id: number;
    date: string;
    dateStart: string;
    dateStop: string;
    hourStart: string;
    hourStop: string; 
    breakStart: string;
    breakStop: string;
    totalHours: string; 
    extraHours: string; 
    dueHours: string; 
    employeeId: number;
    dayTypeId: number;

    constructor(
        id: number,
        date: string,
        dateStart: string,
        dateStop: string,
        hourStart: string,
        hourStop: string,
        breakStart: string,
        breakStop: string,
        totalHours: string,
        extraHours: string,
        dueHours: string,
        employeeId: number,
        dayTypeId: number
    ) {
        this.id = id;
        this.date = date;
        this.dateStart = dateStart;
        this.dateStop = dateStop;
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
