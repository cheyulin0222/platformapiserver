package com.arplanets.platform.dto.service;

import com.arplanets.platform.bo.domain.Statement;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PolicyData {

    private String policyId;

    private String policyName;

    private List<StatementData> statements;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
