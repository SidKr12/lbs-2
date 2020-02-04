package com.infosys.lbs.web.rest;

import com.infosys.lbs.service.GeofenceService;
import com.infosys.lbs.web.rest.errors.BadRequestAlertException;
import com.infosys.lbs.service.dto.GeofenceDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.infosys.lbs.domain.Geofence}.
 */
@RestController
@RequestMapping("/api")
public class GeofenceResource {

    private final Logger log = LoggerFactory.getLogger(GeofenceResource.class);

    private static final String ENTITY_NAME = "geofence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeofenceService geofenceService;

    public GeofenceResource(GeofenceService geofenceService) {
        this.geofenceService = geofenceService;
    }

    /**
     * {@code POST  /geofences} : Create a new geofence.
     *
     * @param geofenceDTO the geofenceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geofenceDTO, or with status {@code 400 (Bad Request)} if the geofence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geofences")
    public ResponseEntity<GeofenceDTO> createGeofence(@RequestBody GeofenceDTO geofenceDTO) throws URISyntaxException {
        log.debug("REST request to save Geofence : {}", geofenceDTO);
        if (geofenceDTO.getId() != null) {
            throw new BadRequestAlertException("A new geofence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeofenceDTO result = geofenceService.save(geofenceDTO);
        return ResponseEntity.created(new URI("/api/geofences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geofences} : Updates an existing geofence.
     *
     * @param geofenceDTO the geofenceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geofenceDTO,
     * or with status {@code 400 (Bad Request)} if the geofenceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geofenceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geofences")
    public ResponseEntity<GeofenceDTO> updateGeofence(@RequestBody GeofenceDTO geofenceDTO) throws URISyntaxException {
        log.debug("REST request to update Geofence : {}", geofenceDTO);
        if (geofenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeofenceDTO result = geofenceService.save(geofenceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geofenceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geofences} : get all the geofences.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geofences in body.
     */
    @GetMapping("/geofences")
    public List<GeofenceDTO> getAllGeofences() {
        log.debug("REST request to get all Geofences");
        return geofenceService.findAll();
    }

    /**
     * {@code GET  /geofences/:id} : get the "id" geofence.
     *
     * @param id the id of the geofenceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geofenceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geofences/{id}")
    public ResponseEntity<GeofenceDTO> getGeofence(@PathVariable Long id) {
        log.debug("REST request to get Geofence : {}", id);
        Optional<GeofenceDTO> geofenceDTO = geofenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geofenceDTO);
    }

    /**
     * {@code DELETE  /geofences/:id} : delete the "id" geofence.
     *
     * @param id the id of the geofenceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geofences/{id}")
    public ResponseEntity<Void> deleteGeofence(@PathVariable Long id) {
        log.debug("REST request to delete Geofence : {}", id);
        geofenceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
