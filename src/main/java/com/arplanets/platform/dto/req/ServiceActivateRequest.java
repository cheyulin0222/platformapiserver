package com.arplanets.platform.dto.req;

import com.arplanets.platform.enums.Activation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class ServiceActivateRequest {

    @NotNull
    @Schema(description = "服務啟用狀態 (ACTIVE-啟用, INACTIVE-停用)")
    private Activation active;
}
