import {
    Component,
    NgModule, OnInit
} from '@angular/core';

import {
    BrowserModule
} from '@angular/platform-browser';

import {
    AgmCoreModule
} from 'angular2-google-maps/core';

@Component({
    selector: 'events-map',
    templateUrl: './maps.component.html',
    styleUrls: ['./maps.css', 'maps.css'],
})
export class MapsComponent implements OnInit{
    // google maps zoom level
    zoom: number = 8;

    // initial center position for the map
    lat: number = 52.14;
    lng: number = 21.1;

    ngOnInit() {
    }

    clickedMarker(label: string, index: number) {
        console.log(`clicked the marker: ${label || index}`)
    }

    mapClicked($event: any) {
        this.markers.push({
            lat: $event.coords.lat,
            lng: $event.coords.lng,
            draggable: false
        });
    }

    markerDragEnd(m: marker, $event: MouseEvent) {
        console.log('dragEnd', m, $event);
    }

    markers: marker[] = [
        {
            lat: 51.673858,
            lng: 7.815982,
            label: 'A',
            draggable: true
        },
        {
            lat: 51.373858,
            lng: 7.215982,
            label: 'B',
            draggable: false
        },
        {
            lat: 51.723858,
            lng: 7.895982,
            label: 'C',
            draggable: true
        }
    ]
}
// just an interface for type safety.
interface marker {
    lat: number;
    lng: number;
    label?: string;
    draggable: boolean;
}
