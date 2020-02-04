package com.infosys.lbs.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.infosys.lbs.web.rest.TestUtil;

public class GeofenceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeofenceDTO.class);
        GeofenceDTO geofenceDTO1 = new GeofenceDTO();
        geofenceDTO1.setId(1L);
        GeofenceDTO geofenceDTO2 = new GeofenceDTO();
        assertThat(geofenceDTO1).isNotEqualTo(geofenceDTO2);
        geofenceDTO2.setId(geofenceDTO1.getId());
        assertThat(geofenceDTO1).isEqualTo(geofenceDTO2);
        geofenceDTO2.setId(2L);
        assertThat(geofenceDTO1).isNotEqualTo(geofenceDTO2);
        geofenceDTO1.setId(null);
        assertThat(geofenceDTO1).isNotEqualTo(geofenceDTO2);
    }
}
