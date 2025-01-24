package com.arplanets.platform.bo.domain;

import com.arplanets.platform.enums.Activation;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Service {

    private String serviceId;

    private String serviceName;

    private String prefix;

    private Activation active;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
