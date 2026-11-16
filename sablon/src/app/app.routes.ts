import { Routes } from '@angular/router';
import { HomeComponent } from './home-component/home-component';
import { SolutionComponent } from './solution-component/solution-component';
import { NopageComponent } from './nopage-component/nopage-component';

export const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'home', loadComponent: () => import('./home-component/home-component')
        .then(m => m.HomeComponent)
    },
    {path: 'solution', loadComponent: () => import('./solution-component/solution-component')
        .then(m => m.SolutionComponent)
    },
    {path: 'employee', loadComponent: () => import('./employee-component/employee-component')
        .then(m => m.EmployeeComponent)
    },
    {path: '**', component: NopageComponent}
];
