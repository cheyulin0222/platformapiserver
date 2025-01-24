package com.arplanets.platform.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ServiceCreateRequest {

    @NotBlank
    @Schema(description = "服務名稱")
    private String serviceName;

    @NotBlank
    @Schema(description = "服務前墜名稱")
    private String prefix;
}
