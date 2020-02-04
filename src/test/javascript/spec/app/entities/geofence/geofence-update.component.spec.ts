import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LbsLiveEnterpriseTestModule } from '../../../test.module';
import { GeofenceUpdateComponent } from 'app/entities/geofence/geofence-update.component';
import { GeofenceService } from 'app/entities/geofence/geofence.service';
import { Geofence } from 'app/shared/model/geofence.model';

describe('Component Tests', () => {
  describe('Geofence Management Update Component', () => {
    let comp: GeofenceUpdateComponent;
    let fixture: ComponentFixture<GeofenceUpdateComponent>;
    let service: GeofenceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LbsLiveEnterpriseTestModule],
        declarations: [GeofenceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GeofenceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeofenceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeofenceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Geofence(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Geofence();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
