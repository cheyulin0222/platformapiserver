package com.arplanets.platform.dto.service;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OrganizationQuotaData {

    private String orgQuotaId;

    private String orgId;

    private String quotaId;

    private BigDecimal customValue;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
