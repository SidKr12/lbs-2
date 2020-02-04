import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeofence, Geofence } from 'app/shared/model/geofence.model';
import { GeofenceService } from './geofence.service';

@Component({
  selector: 'jhi-geofence-update',
  templateUrl: './geofence-update.component.html'
})
export class GeofenceUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    fenceId: [],
    fenceName: [],
    createdBy: [],
    createdTime: [],
    modifiedBy: [],
    modifiedTime: [],
    type: [],
    fenceCode: []
  });

  constructor(protected geofenceService: GeofenceService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geofence }) => {
      this.updateForm(geofence);
    });
  }

  updateForm(geofence: IGeofence): void {
    this.editForm.patchValue({
      id: geofence.id,
      fenceId: geofence.fenceId,
      fenceName: geofence.fenceName,
      createdBy: geofence.createdBy,
      createdTime: geofence.createdTime,
      modifiedBy: geofence.modifiedBy,
      modifiedTime: geofence.modifiedTime,
      type: geofence.type,
      fenceCode: geofence.fenceCode
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geofence = this.createFromForm();
    if (geofence.id !== undefined) {
      this.subscribeToSaveResponse(this.geofenceService.update(geofence));
    } else {
      this.subscribeToSaveResponse(this.geofenceService.create(geofence));
    }
  }

  private createFromForm(): IGeofence {
    return {
      ...new Geofence(),
      id: this.editForm.get(['id'])!.value,
      fenceId: this.editForm.get(['fenceId'])!.value,
      fenceName: this.editForm.get(['fenceName'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdTime: this.editForm.get(['createdTime'])!.value,
      modifiedBy: this.editForm.get(['modifiedBy'])!.value,
      modifiedTime: this.editForm.get(['modifiedTime'])!.value,
      type: this.editForm.get(['type'])!.value,
      fenceCode: this.editForm.get(['fenceCode'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeofence>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
