export class HoursPerWeek {
    id: number;
    hours: number;
    enable: boolean;

    constructor(
        id: number,
        hours: number,
        enable: boolean
    ) {
        this.id = id;
        this.hours=  hours;
        this.enable = enable;
    }
}
