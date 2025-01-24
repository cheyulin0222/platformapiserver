package com.arplanets.platform.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuotaCreateResponse {

    @Schema(description = "服務額度資訊")
    private QuotaResponse data;

    @Schema(description = "新增結果")
    private String message;
}
