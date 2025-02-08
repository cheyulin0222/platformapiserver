package com.arplanets.platform.dto.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
