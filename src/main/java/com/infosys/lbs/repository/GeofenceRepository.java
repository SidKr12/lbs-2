package com.infosys.lbs.repository;

import com.infosys.lbs.domain.Geofence;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Geofence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeofenceRepository extends JpaRepository<Geofence, Long> {

}
