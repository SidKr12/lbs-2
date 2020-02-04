package com.infosys.lbs.service.mapper;

import com.infosys.lbs.domain.*;
import com.infosys.lbs.service.dto.GeofenceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Geofence} and its DTO {@link GeofenceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GeofenceMapper extends EntityMapper<GeofenceDTO, Geofence> {



    default Geofence fromId(Long id) {
        if (id == null) {
            return null;
        }
        Geofence geofence = new Geofence();
        geofence.setId(id);
        return geofence;
    }
}
