package com.arplanets.platform.dto.service;

import com.arplanets.platform.enums.AccountLevel;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrganizationData {

    private String orgId;

    private String orgName;

    private AccountLevel level;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
