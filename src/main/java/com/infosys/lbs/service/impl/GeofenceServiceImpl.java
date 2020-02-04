package com.infosys.lbs.service.impl;

import com.infosys.lbs.service.GeofenceService;
import com.infosys.lbs.domain.Geofence;
import com.infosys.lbs.repository.GeofenceRepository;
import com.infosys.lbs.service.dto.GeofenceDTO;
import com.infosys.lbs.service.mapper.GeofenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Geofence}.
 */
@Service
@Transactional
public class GeofenceServiceImpl implements GeofenceService {

    private final Logger log = LoggerFactory.getLogger(GeofenceServiceImpl.class);

    private final GeofenceRepository geofenceRepository;

    private final GeofenceMapper geofenceMapper;

    public GeofenceServiceImpl(GeofenceRepository geofenceRepository, GeofenceMapper geofenceMapper) {
        this.geofenceRepository = geofenceRepository;
        this.geofenceMapper = geofenceMapper;
    }

    /**
     * Save a geofence.
     *
     * @param geofenceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GeofenceDTO save(GeofenceDTO geofenceDTO) {
        log.debug("Request to save Geofence : {}", geofenceDTO);
        Geofence geofence = geofenceMapper.toEntity(geofenceDTO);
        geofence = geofenceRepository.save(geofence);
        return geofenceMapper.toDto(geofence);
    }

    /**
     * Get all the geofences.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<GeofenceDTO> findAll() {
        log.debug("Request to get all Geofences");
        return geofenceRepository.findAll().stream()
            .map(geofenceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one geofence by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GeofenceDTO> findOne(Long id) {
        log.debug("Request to get Geofence : {}", id);
        return geofenceRepository.findById(id)
            .map(geofenceMapper::toDto);
    }

    /**
     * Delete the geofence by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Geofence : {}", id);
        geofenceRepository.deleteById(id);
    }
}
