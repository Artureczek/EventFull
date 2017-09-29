import { Route } from '@angular/router';
import {AddEventsComponent} from "./add-events.component";


export const addEventsRoute: Route = {
    path: 'add-events',
    component: AddEventsComponent,
    data: {
        pageTitle: 'Dodaj nowy event!'
    }
};
