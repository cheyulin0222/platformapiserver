package com.arplanets.platform.dto.req;

import com.arplanets.platform.enums.AccountLevel;
import com.arplanets.platform.enums.Adjustability;
import com.arplanets.platform.enums.UnitType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class QuotaUpdateRequest {

    @Schema(description = "服務額度名稱")
    private String quotaName;

    @Schema(description = "服務額度單位")
    private UnitType unitType;

    @Schema(description = "服務額度變更方式")
    private Adjustability adjustability;

    @Schema(description = "預設服務額度")
    private BigDecimal defaultValue;
}
