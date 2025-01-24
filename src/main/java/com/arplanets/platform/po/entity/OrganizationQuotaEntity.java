package com.arplanets.platform.po.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "org_quota")
@Data
@Builder
public class OrganizationQuotaEntity {

    @Id
    @Column(name = "org_quota_id")
    private String orgQuotaId;

    @Column(name = "quota_id")
    private String quotaId;

    @Column(name = "org_id")
    private String orgId;

    private Integer value;

    private Integer utilization;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = false, insertable = false)
    private LocalDateTime updatedAt;
}
