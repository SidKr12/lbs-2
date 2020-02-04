package com.infosys.lbs.service;

import com.infosys.lbs.service.dto.GeofenceDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.infosys.lbs.domain.Geofence}.
 */
public interface GeofenceService {

    /**
     * Save a geofence.
     *
     * @param geofenceDTO the entity to save.
     * @return the persisted entity.
     */
    GeofenceDTO save(GeofenceDTO geofenceDTO);

    /**
     * Get all the geofences.
     *
     * @return the list of entities.
     */
    List<GeofenceDTO> findAll();


    /**
     * Get the "id" geofence.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GeofenceDTO> findOne(Long id);

    /**
     * Delete the "id" geofence.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
