package com.infosys.lbs.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.infosys.lbs.domain.Geofence} entity.
 */
public class GeofenceDTO implements Serializable {

    private Long id;

    private Long fenceId;

    private String fenceName;

    private String createdBy;

    private Long createdTime;

    private String modifiedBy;

    private Long modifiedTime;

    private Long type;

    private String fenceCode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFenceId() {
        return fenceId;
    }

    public void setFenceId(Long fenceId) {
        this.fenceId = fenceId;
    }

    public String getFenceName() {
        return fenceName;
    }

    public void setFenceName(String fenceName) {
        this.fenceName = fenceName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getFenceCode() {
        return fenceCode;
    }

    public void setFenceCode(String fenceCode) {
        this.fenceCode = fenceCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GeofenceDTO geofenceDTO = (GeofenceDTO) o;
        if (geofenceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), geofenceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GeofenceDTO{" +
            "id=" + getId() +
            ", fenceId=" + getFenceId() +
            ", fenceName='" + getFenceName() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdTime=" + getCreatedTime() +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", modifiedTime=" + getModifiedTime() +
            ", type=" + getType() +
            ", fenceCode='" + getFenceCode() + "'" +
            "}";
    }
}
