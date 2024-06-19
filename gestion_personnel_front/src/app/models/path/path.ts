export class Path {

    id: number;
    name: string;
    urlPath: string;
    alias: string;
    enable: boolean;
    listIdPathAssigned: number[];

    constructor(id: number, name: string, urlPath: string, alias: string, enable: boolean, listIdPathAssigned: number[]) {
        this.id = id;
        this.name = name;
        this.urlPath = urlPath;
        this.alias = alias;
        this.enable = enable;
        this.listIdPathAssigned = listIdPathAssigned;
    }

}
