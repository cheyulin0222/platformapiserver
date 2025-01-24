package com.arplanets.platform.bo.domain;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OrganizationQuota {

    private String orgQuotaId;

    private String orgId;

    private String quotaId;

    private BigDecimal customValue;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
