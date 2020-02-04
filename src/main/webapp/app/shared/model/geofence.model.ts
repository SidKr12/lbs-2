export interface IGeofence {
  id?: number;
  fenceId?: number;
  fenceName?: string;
  createdBy?: string;
  createdTime?: number;
  modifiedBy?: string;
  modifiedTime?: number;
  type?: number;
  fenceCode?: string;
}

export class Geofence implements IGeofence {
  constructor(
    public id?: number,
    public fenceId?: number,
    public fenceName?: string,
    public createdBy?: string,
    public createdTime?: number,
    public modifiedBy?: string,
    public modifiedTime?: number,
    public type?: number,
    public fenceCode?: string
  ) {}
}
