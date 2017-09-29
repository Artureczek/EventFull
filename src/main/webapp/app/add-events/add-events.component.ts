import {Component, NgModule, OnInit, ElementRef, ViewChild, NgZone} from '@angular/core';
import {FormControl, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserModule} from "@angular/platform-browser";
import {AgmCoreModule, MapsAPILoader} from '@agm/core';

@Component({
    selector: 'jhi-add-events',
    templateUrl: './add-events.component.html',
    styleUrls: ['./add-events.css', 'add-events.css'],
})
export class AddEventsComponent implements OnInit {

    zoom: number = 8;
    public searchControl: FormControl;
    lat: number = 52.14;
    lng: number = 21.1;

    @ViewChild("search")
    public searchElementRef: ElementRef;

    constructor(private mapsAPILoader: MapsAPILoader,
                private ngZone: NgZone) {
    }

    ngOnInit() {
        this.searchControl = new FormControl();

        this.setCurrentPosition();

        this.mapsAPILoader.load().then(() => {
            let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
                types: ["address"]
            });
            autocomplete.addListener("place_changed", () => {
                this.ngZone.run(() => {
                    //get the place result
                    let place: google.maps.places.PlaceResult = autocomplete.getPlace();

                    //verify result
                    if (place.geometry === undefined || place.geometry === null) {
                        return;
                    }

                    //set latitude, longitude and zoom
                    this.lat = place.geometry.location.lat();
                    this.lng = place.geometry.location.lng();
                    this.zoom = 12;
                });
            });
        });
    }

    private setCurrentPosition() {
        if ("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition((position) => {
                this.lat = position.coords.latitude;
                this.lng = position.coords.longitude;
                this.zoom = 12;
            });
        }
    }

    clickedMarker(label: string, index: number) {
        console.log(`clicked the marker: ${label || index}`)
    }

    submit(){

    }

    events: event[] = [
        {
            value: '1',
            label: 'impreza'
        },
        {
            value: '2',
            label: 'koncert'
        },
        {
            value: '3',
            label: 'spotkanie z celebrytą'
        },
        {
            value: '4',
            label: 'darmowe żarcie'
        },
        {
            value: '4',
            label: 'ogarnianie o co biega z włosami Szpili'
        },
    ]
}
// just an interface for type safety.
interface event {
    value: string;
    label: string;
}
