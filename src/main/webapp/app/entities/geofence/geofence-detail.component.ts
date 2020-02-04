import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGeofence } from 'app/shared/model/geofence.model';

@Component({
  selector: 'jhi-geofence-detail',
  templateUrl: './geofence-detail.component.html'
})
export class GeofenceDetailComponent implements OnInit {
  geofence: IGeofence | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geofence }) => {
      this.geofence = geofence;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
