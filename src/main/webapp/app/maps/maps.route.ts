import { Route } from '@angular/router';

import { MapsComponent } from './maps.component';

export const mapsRoute: Route = {
    path: 'event-map',
    component: MapsComponent,
    data: {
        pageTitle: 'Mapa eventow'
    }
};
