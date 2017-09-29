import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AgmCoreModule } from '@agm/core';
import {RouterModule} from "@angular/router";
import {ReactiveFormsModule, FormsModule} from "@angular/forms";
import {AddEventsComponent} from "./add-events.component";
import {addEventsRoute} from "./add-events.route";

@NgModule({
    imports: [
        BrowserModule,
        AgmCoreModule.forRoot({
            apiKey: 'AIzaSyCJ2IL3pUGBOistX3iqTCZp_Y5nz8IDyQk',
            libraries: ["places"]
        }),
        RouterModule.forRoot([ addEventsRoute ], { useHash: true }),
        FormsModule,
        ReactiveFormsModule
    ],
    declarations: [ AddEventsComponent ],
    bootstrap: [ AddEventsComponent ]
})
export class AddEventsModule {}
