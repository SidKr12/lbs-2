import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IGeofence } from 'app/shared/model/geofence.model';
import { GeofenceService } from './geofence.service';
import { GeofenceDeleteDialogComponent } from './geofence-delete-dialog.component';

@Component({
  selector: 'jhi-geofence',
  templateUrl: './geofence.component.html'
})
export class GeofenceComponent implements OnInit, OnDestroy {
  geofences?: IGeofence[];
  eventSubscriber?: Subscription;

  constructor(protected geofenceService: GeofenceService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.geofenceService.query().subscribe((res: HttpResponse<IGeofence[]>) => {
      this.geofences = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInGeofences();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IGeofence): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInGeofences(): void {
    this.eventSubscriber = this.eventManager.subscribe('geofenceListModification', () => this.loadAll());
  }

  delete(geofence: IGeofence): void {
    const modalRef = this.modalService.open(GeofenceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.geofence = geofence;
  }
}
