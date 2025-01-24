package com.arplanets.platform.bo.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class QuotaUsage {

    private String quotaUsageId;
    private String orgId;
    private String quotaId;
    private BigDecimal used;
    private LocalDateTime updatedAt;
}
