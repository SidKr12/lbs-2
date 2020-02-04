import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LbsLiveEnterpriseTestModule } from '../../../test.module';
import { GeofenceDetailComponent } from 'app/entities/geofence/geofence-detail.component';
import { Geofence } from 'app/shared/model/geofence.model';

describe('Component Tests', () => {
  describe('Geofence Management Detail Component', () => {
    let comp: GeofenceDetailComponent;
    let fixture: ComponentFixture<GeofenceDetailComponent>;
    const route = ({ data: of({ geofence: new Geofence(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LbsLiveEnterpriseTestModule],
        declarations: [GeofenceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GeofenceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeofenceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geofence on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geofence).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
