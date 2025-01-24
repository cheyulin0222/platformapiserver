package com.arplanets.platform.dto.service.req;

import com.arplanets.platform.enums.AccountLevel;
import com.arplanets.platform.enums.Adjustability;
import com.arplanets.platform.enums.UnitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuotaUpdateData {

    private String quotaName;

    private UnitType unitType;

    private Adjustability adjustability;

    private Map<AccountLevel, BigDecimal> defaultValues;
}
