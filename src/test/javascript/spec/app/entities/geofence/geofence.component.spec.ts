import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LbsLiveEnterpriseTestModule } from '../../../test.module';
import { GeofenceComponent } from 'app/entities/geofence/geofence.component';
import { GeofenceService } from 'app/entities/geofence/geofence.service';
import { Geofence } from 'app/shared/model/geofence.model';

describe('Component Tests', () => {
  describe('Geofence Management Component', () => {
    let comp: GeofenceComponent;
    let fixture: ComponentFixture<GeofenceComponent>;
    let service: GeofenceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LbsLiveEnterpriseTestModule],
        declarations: [GeofenceComponent],
        providers: []
      })
        .overrideTemplate(GeofenceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeofenceComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeofenceService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Geofence(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.geofences && comp.geofences[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
