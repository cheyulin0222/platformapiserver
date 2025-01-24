package com.arplanets.platform.dto.res;

import com.arplanets.platform.bo.domain.OrganizationQuota;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationQuotaCreateResponse {

    @Schema(description = "組織服務額度資訊")
    private OrganizationQuotaResponse data;

    @Schema(description = "新增結果")
    private String message;
}
