export class User {

    id: number;
    secondname: string;
    firstname: string;
    roleName: string;
    enable: boolean;

    constructor(
        id: number,
        secondname: string,
        firstname: string,
        rolename: string,
        enable: boolean
    ) {
        this.id = id;
        this.secondname = secondname;
        this.firstname = firstname;
        this.roleName = rolename;
        this.enable = enable;
    }

}
