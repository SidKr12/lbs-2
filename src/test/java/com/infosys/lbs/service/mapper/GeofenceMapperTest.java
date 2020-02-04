package com.infosys.lbs.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class GeofenceMapperTest {

    private GeofenceMapper geofenceMapper;

    @BeforeEach
    public void setUp() {
        geofenceMapper = new GeofenceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(geofenceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geofenceMapper.fromId(null)).isNull();
    }
}
