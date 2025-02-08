package com.arplanets.platform.bo.domain;

import com.arplanets.platform.enums.Activation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    private String serviceId;

    private String serviceName;

    private String prefix;

    private Activation active;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
