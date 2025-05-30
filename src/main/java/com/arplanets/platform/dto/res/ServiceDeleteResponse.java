package com.arplanets.platform.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceDeleteResponse {

    @Schema(description = "刪除結果")
    private String message;
}
