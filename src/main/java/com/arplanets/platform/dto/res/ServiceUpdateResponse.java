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
public class ServiceUpdateResponse {

    @Schema(description = "服務資訊")
    private ServiceResponse serviceInfo;

    @Schema(description = "修改結果")
    private String message;
}
