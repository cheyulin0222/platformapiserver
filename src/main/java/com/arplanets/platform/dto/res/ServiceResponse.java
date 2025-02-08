package com.arplanets.platform.dto.res;

import com.arplanets.platform.enums.Activation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse {

    @Schema(description = "服務 ID (格式：SERVICE-yyyyMMddHHmmss-UUID)")
    private String serviceId;

    @Schema(description = "服務名稱")
    private String serviceName;

    @Schema(description = "服務前墜名稱")
    private String prefix;

    @Schema(description = "服務啟用狀態 (ACTIVE-啟用, INACTIVE-停用)")
    private Activation active;

    @Schema(description = "建立建立人員帳號")
    private String createdBy;

    @Schema(description = "服務最後修改人員帳號")
    private String updatedBy;

    @Schema(description = "服務建立時間 (系統自動產生，格式：yyyy-MM-ddTHH:mm:ss)")
    private LocalDateTime createdAt;

    @Schema(description = "服務最後修改時間 (系統自動更新，格式：yyyy-MM-ddTHH:mm:ss)")
    private LocalDateTime updatedAt;
}
