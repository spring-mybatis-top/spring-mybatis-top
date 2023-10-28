export interface IHelloWorld {
  id?: number;
  name?: string | null;
  age?: number | null;
}

export class HelloWorld implements IHelloWorld {
  constructor(public id?: number, public name?: string | null, public age?: number | null) {}
}
