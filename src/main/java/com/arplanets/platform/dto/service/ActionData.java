package com.arplanets.platform.dto.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActionData {

    private String actionId;

    private String actionName;

    private String serviceId;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
