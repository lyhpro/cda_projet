export class PwdForm {

    tokenId: number;
    password: string;
    confirmedPassword: string;

    constructor(
        tokenId: number,
        password: string,
        confirmedPassword: string
    ) {
        this.tokenId = tokenId;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }
}
