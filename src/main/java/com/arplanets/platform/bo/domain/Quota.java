package com.arplanets.platform.bo.domain;

import com.arplanets.platform.enums.AccountLevel;
import com.arplanets.platform.enums.Adjustability;
import com.arplanets.platform.enums.UnitType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;


@Data
@Builder
public class Quota {

    private String quotaId;

    private String serviceId;

    private String quotaName;

    private UnitType unitType;

    private Adjustability adjustability;

    private Map<AccountLevel, BigDecimal> defaultValues;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
