import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LbsLiveEnterpriseSharedModule } from 'app/shared/shared.module';
import { GeofenceComponent } from './geofence.component';
import { GeofenceDetailComponent } from './geofence-detail.component';
import { GeofenceUpdateComponent } from './geofence-update.component';
import { GeofenceDeleteDialogComponent } from './geofence-delete-dialog.component';
import { geofenceRoute } from './geofence.route';

@NgModule({
  imports: [LbsLiveEnterpriseSharedModule, RouterModule.forChild(geofenceRoute)],
  declarations: [GeofenceComponent, GeofenceDetailComponent, GeofenceUpdateComponent, GeofenceDeleteDialogComponent],
  entryComponents: [GeofenceDeleteDialogComponent]
})
export class LbsLiveEnterpriseGeofenceModule {}
