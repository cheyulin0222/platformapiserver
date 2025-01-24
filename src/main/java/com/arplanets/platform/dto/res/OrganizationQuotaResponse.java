package com.arplanets.platform.dto.res;

import com.arplanets.platform.enums.Adjustability;
import com.arplanets.platform.enums.UnitType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OrganizationQuotaResponse {

    private String orgQuotaId;

    private String quotaId;

    private String quotaName;

    private UnitType unitType;

    private Adjustability adjustability;

    private BigDecimal defaultValue;

    private BigDecimal customValue;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
