package com.infosys.lbs.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Geofence.
 */
@Entity
@Table(name = "geofence")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Geofence implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "fence_id")
    private Long fenceId;

    @Column(name = "fence_name")
    private String fenceName;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_time")
    private Long createdTime;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_time")
    private Long modifiedTime;

    @Column(name = "type")
    private Long type;

    @Column(name = "fence_code")
    private String fenceCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFenceId() {
        return fenceId;
    }

    public Geofence fenceId(Long fenceId) {
        this.fenceId = fenceId;
        return this;
    }

    public void setFenceId(Long fenceId) {
        this.fenceId = fenceId;
    }

    public String getFenceName() {
        return fenceName;
    }

    public Geofence fenceName(String fenceName) {
        this.fenceName = fenceName;
        return this;
    }

    public void setFenceName(String fenceName) {
        this.fenceName = fenceName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Geofence createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public Geofence createdTime(Long createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public Geofence modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Long getModifiedTime() {
        return modifiedTime;
    }

    public Geofence modifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
        return this;
    }

    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getType() {
        return type;
    }

    public Geofence type(Long type) {
        this.type = type;
        return this;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getFenceCode() {
        return fenceCode;
    }

    public Geofence fenceCode(String fenceCode) {
        this.fenceCode = fenceCode;
        return this;
    }

    public void setFenceCode(String fenceCode) {
        this.fenceCode = fenceCode;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Geofence)) {
            return false;
        }
        return id != null && id.equals(((Geofence) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Geofence{" +
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
