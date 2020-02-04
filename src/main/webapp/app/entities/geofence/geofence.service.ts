import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeofence } from 'app/shared/model/geofence.model';

type EntityResponseType = HttpResponse<IGeofence>;
type EntityArrayResponseType = HttpResponse<IGeofence[]>;

@Injectable({ providedIn: 'root' })
export class GeofenceService {
  public resourceUrl = SERVER_API_URL + 'api/geofences';

  constructor(protected http: HttpClient) {}

  create(geofence: IGeofence): Observable<EntityResponseType> {
    return this.http.post<IGeofence>(this.resourceUrl, geofence, { observe: 'response' });
  }

  update(geofence: IGeofence): Observable<EntityResponseType> {
    return this.http.put<IGeofence>(this.resourceUrl, geofence, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeofence>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeofence[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
