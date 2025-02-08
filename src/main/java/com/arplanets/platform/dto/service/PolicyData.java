package com.arplanets.platform.dto.service;

import com.arplanets.platform.bo.domain.Statement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PolicyData {

    private String policyId;

    private String policyName;

    private List<StatementData> statements;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
