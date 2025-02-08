package com.arplanets.platform.bo.domain;

import com.arplanets.platform.enums.AccountLevel;
import com.arplanets.platform.enums.Activation;
import com.arplanets.platform.enums.Adjustability;
import com.arplanets.platform.enums.UnitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quota {

    private String quotaId;

    private String serviceId;

    private String quotaName;

    private Activation active;

    private UnitType unitType;

    private Adjustability adjustability;

    private BigDecimal defaultValue;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
