import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AgmCoreModule } from '@agm/core';
import { MapsComponent} from './maps.component';
import {RouterModule} from "@angular/router";
import {mapsRoute} from "./maps.route";

@NgModule({
    imports: [
        BrowserModule,
        AgmCoreModule.forRoot({
            apiKey: 'AIzaSyCJ2IL3pUGBOistX3iqTCZp_Y5nz8IDyQk'
        }),
        RouterModule.forRoot([ mapsRoute ], { useHash: true })
    ],
    declarations: [ MapsComponent ],
    bootstrap: [ MapsComponent ]
})
export class MapsModule {}
