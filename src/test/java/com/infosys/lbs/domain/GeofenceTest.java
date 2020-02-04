package com.infosys.lbs.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.infosys.lbs.web.rest.TestUtil;

public class GeofenceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Geofence.class);
        Geofence geofence1 = new Geofence();
        geofence1.setId(1L);
        Geofence geofence2 = new Geofence();
        geofence2.setId(geofence1.getId());
        assertThat(geofence1).isEqualTo(geofence2);
        geofence2.setId(2L);
        assertThat(geofence1).isNotEqualTo(geofence2);
        geofence1.setId(null);
        assertThat(geofence1).isNotEqualTo(geofence2);
    }
}
