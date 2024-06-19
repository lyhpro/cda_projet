export class Role {
    id: number;
    name: string;
    description: string;
    enable: boolean;

    constructor(
        id: number, 
        name: string, 
        description: string, 
        enable: boolean
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enable = enable;
    }
}
