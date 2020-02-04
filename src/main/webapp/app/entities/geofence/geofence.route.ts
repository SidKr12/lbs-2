import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeofence, Geofence } from 'app/shared/model/geofence.model';
import { GeofenceService } from './geofence.service';
import { GeofenceComponent } from './geofence.component';
import { GeofenceDetailComponent } from './geofence-detail.component';
import { GeofenceUpdateComponent } from './geofence-update.component';

@Injectable({ providedIn: 'root' })
export class GeofenceResolve implements Resolve<IGeofence> {
  constructor(private service: GeofenceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeofence> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geofence: HttpResponse<Geofence>) => {
          if (geofence.body) {
            return of(geofence.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Geofence());
  }
}

export const geofenceRoute: Routes = [
  {
    path: '',
    component: GeofenceComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lbsLiveEnterpriseApp.geofence.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GeofenceDetailComponent,
    resolve: {
      geofence: GeofenceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lbsLiveEnterpriseApp.geofence.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GeofenceUpdateComponent,
    resolve: {
      geofence: GeofenceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lbsLiveEnterpriseApp.geofence.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GeofenceUpdateComponent,
    resolve: {
      geofence: GeofenceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'lbsLiveEnterpriseApp.geofence.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
