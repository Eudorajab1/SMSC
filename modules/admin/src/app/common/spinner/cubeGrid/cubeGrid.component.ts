import {Component, Input, OnDestroy} from "@angular/core";

@Component({
    moduleId: module.id,
    selector: 'sk-cube-grid',
    styles: [
        require('./cubeGrid.scss')
    ],
    template: require('./cubeGrid.html')
})

export class CubeGridComponent {
    @Input()
    public delay:number = 0;

    @Input()
    public backgroundColor:string = '#009688';

    @Input()
    public isRunning:boolean = true;

}
