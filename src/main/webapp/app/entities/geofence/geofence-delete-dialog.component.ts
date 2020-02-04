import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeofence } from 'app/shared/model/geofence.model';
import { GeofenceService } from './geofence.service';

@Component({
  templateUrl: './geofence-delete-dialog.component.html'
})
export class GeofenceDeleteDialogComponent {
  geofence?: IGeofence;

  constructor(protected geofenceService: GeofenceService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geofenceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geofenceListModification');
      this.activeModal.close();
    });
  }
}
