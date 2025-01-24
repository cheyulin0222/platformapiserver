package com.arplanets.platform.bo.domain;

import com.arplanets.platform.enums.PolicyType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Policy {

    private String policyId;

    private String policyName;

    private List<Statement> statements;

    private PolicyType policyType;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
