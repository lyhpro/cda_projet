export class Employee {
    id: number;
    secondname: string;
    firstname: string;
    placeOfBirth: string;
    dateOfBirth: Date;
    enable: boolean;
    dateOfCreation: Date;
    contactDetailId: number;
    professionalDetailId: number;

    constructor(
        id: number,
        secondname: string,
        firstname: string,
        placeOfBirth: string,
        dateOfBirth: Date,
        enable: boolean,
        dateOfCreation: Date,
        contactDetailId: number,
        professionalDetailId: number
    ) {
        this.id = id;
        this.secondname = secondname;
        this.firstname = firstname;
        this.placeOfBirth = placeOfBirth;
        this.dateOfBirth = dateOfBirth;
        this.enable = enable;
        this.dateOfCreation = dateOfCreation;
        this.contactDetailId = contactDetailId;
        this.professionalDetailId = professionalDetailId;
    }
}
