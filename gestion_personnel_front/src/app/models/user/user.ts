export class User {

    id: number;
    secondname: string;
    firstname: string;
    roleName: string;
    enable: boolean;
    passwordUpdated: boolean;

    constructor(
        id: number,
        secondname: string,
        firstname: string,
        rolename: string,
        enable: boolean,
        passwowrdUpdated: boolean
    ) {
        this.id = id;
        this.secondname = secondname;
        this.firstname = firstname;
        this.roleName = rolename;
        this.enable = enable;
        this.passwordUpdated = passwowrdUpdated;
    }

}
