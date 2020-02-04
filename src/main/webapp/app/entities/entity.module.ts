import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'geofence',
        loadChildren: () => import('./geofence/geofence.module').then(m => m.LbsLiveEnterpriseGeofenceModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class LbsLiveEnterpriseEntityModule {}
