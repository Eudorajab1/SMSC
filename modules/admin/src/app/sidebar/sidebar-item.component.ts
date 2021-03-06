import {Component, Input, trigger, style, animate, state, transition} from "@angular/core";
import {Dashboard} from "../dashboard/dashboard.model";
import {Router} from "@angular/router";
import {SidebarModel} from "./sidebar.model";
import {DashboardService, REPOSITORY_NAME} from "../dashboard/dashboard.service";

@Component({
    selector: 'sidebar-item',
    templateUrl: './sidebar-item.component.html',
    providers: [],
    animations: [
        trigger('state', [
            state('closed', style({height: 0})),
            state('open', style({height: '*'})),
            transition('closed => open', [animate('200ms ease-out')]),
            transition('open => closed', [animate('200ms ease-out')])
        ]),
    ],
    styleUrls: ['./sidebar-item.component.scss']
})
export class SidebarItemComponent {

    @Input('sidebarItem') public sidebarItem: SidebarModel;

    @Input('dashboards') public dashboards: Dashboard[] = [];

    public navItemState: boolean[] = [];

    constructor(public router: Router,
                public dashboardService: DashboardService) {
    }

    isActive(instruction: any[]): boolean {
        return this.router.isActive(this.router.createUrlTree(instruction), true);
    }

    navigate(sidebarItem: SidebarModel) {
        if (sidebarItem.name === 'DASHBOARDS') {
            this.dashboardService.getResources(null, null, <Dashboard>{name: 'default'})
                .subscribe((dashboards: Dashboard[]) => {
                    let dashboard = dashboards['_embedded'][REPOSITORY_NAME][0];
                    this.router.navigate(['/dashboard', dashboard['id']]);
                });
        } else {
            this.router.navigateByUrl(sidebarItem.path);
        }
    }
}
