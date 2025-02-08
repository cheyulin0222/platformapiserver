package com.arplanets.platform.dto.res;

import com.arplanets.platform.enums.AccountLevel;
import com.arplanets.platform.enums.Adjustability;
import com.arplanets.platform.enums.UnitType;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class QuotaResponse {

    @Schema(description = "服務額度 ID (格式：QUOTA-yyyyMMddHHmmss-UUID)")
    private String quotaId;

    @Schema(description = "服務 ID (格式：SERVICE-yyyyMMddHHmmss-UUID)")
    private String serviceId;

    @Schema(description = "服務額度名稱")
    private String quotaName;

    @Schema(description = "服務額度單位")
    private UnitType unitType;

    @Schema(description = "服務額度變更方式")
    private Adjustability adjustability;

    @Schema(description = "預設服務額度")
    private BigDecimal defaultValue;

    @Schema(description = "建立建立人員帳號")
    private String createdBy;

    @Schema(description = "服務最後修改人員帳號")
    private String updatedBy;

    @Schema(description = "服務建立時間 (系統自動產生，格式：yyyy-MM-ddTHH:mm:ss)")
    private LocalDateTime createdAt;

    @Schema(description = "服務最後修改時間 (系統自動更新，格式：yyyy-MM-ddTHH:mm:ss)")
    private LocalDateTime updatedAt;
}
