import './vendor.ts';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Ng2Webstorage } from 'ng2-webstorage';
import { EventFullSharedModule, UserRouteAccessService } from './shared';
import { EventFullHomeModule } from './home/home.module';
import { EventFullAdminModule } from './admin/admin.module';
import { EventFullAccountModule } from './account/account.module';
import { EventFullEntityModule } from './entities/entity.module';
import { customHttpProvider } from './blocks/interceptor/http.provider';
import { PaginationConfig } from './blocks/config/uib-pagination.config';

// jhipster-needle-angular-add-module-import JHipster will add new module here

import {
    JhiMainComponent,
    LayoutRoutingModule,
    NavbarComponent,
    FooterComponent,
    ProfileService,
    PageRibbonComponent,
    ErrorComponent
} from './layouts';
import { MapsComponent } from './maps/maps.component';
import {MapsModule} from "./maps/maps.module";
import { AddEventsComponent } from './add-events/add-events.component';
import {AddEventsModule} from "./add-events/add-events.module";

@NgModule({
    imports: [
        BrowserModule,
        LayoutRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-'}),
        EventFullSharedModule,
        EventFullHomeModule,
        EventFullAdminModule,
        EventFullAccountModule,
        EventFullEntityModule,
        MapsModule,
        AddEventsModule
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        FooterComponent
    ],
    providers: [
        ProfileService,
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService
    ],
    bootstrap: [ JhiMainComponent ]
})
export class EventFullAppModule {}
