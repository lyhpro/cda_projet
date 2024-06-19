export class SignupRequest {

    secondname: string;
    firstname: string;
    email: string;
    pwd: string;
    roleName: string;

    constructor(
        secondname: string, 
        firstname: string, 
        email: string, 
        pwd: string, 
        roleName: string
    ) {
        this.secondname = secondname;
        this.firstname = firstname;
        this.email = email;
        this.pwd = pwd;
        this.roleName = roleName;
    }
}