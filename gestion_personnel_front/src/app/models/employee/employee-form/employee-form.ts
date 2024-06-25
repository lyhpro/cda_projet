export class EmployeeForm {
    id: number;
    secondname: string;
    firstname: string;
    placeOfBirth: string;
    dateOfBirth: Date;

    email: string;
    address: string;
    postalCode: number;
    city: string;
    homenumber: number;
    phonenumber: number;

    post: string;
    dateOfHiring: Date;
    dateEndOfContract: Date;
    salary: number;
    hoursPerWeekId: number;
    contractId: number;
    departmentId: number;

    constructor(
        id: number,
        secondname: string,
        firstname: string,
        placeOfBirth: string,
        dateOfBirth: Date,

        email: string,
        address: string,
        postalCode: number,
        city: string,
        homenumber: number,
        phonenumber: number,

        post: string,
        dateOfHiring: Date,
        dateEndOfContract: Date,
        salary: number,
        hoursPerWeekId: number,
        contractId: number,
        departmentId: number
    ) {
        this.id = id;
        this.secondname = secondname;
        this.firstname = firstname;
        this.placeOfBirth = placeOfBirth;
        this.dateOfBirth = dateOfBirth;

        this.email = email;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.homenumber = homenumber;
        this.phonenumber = phonenumber;

        this.post = post;
        this.dateOfHiring = dateOfHiring;
        this.dateEndOfContract = dateEndOfContract;
        this.salary = salary;
        this.hoursPerWeekId = hoursPerWeekId;
        this.contractId =  contractId;
        this.departmentId = departmentId;
    }
}
