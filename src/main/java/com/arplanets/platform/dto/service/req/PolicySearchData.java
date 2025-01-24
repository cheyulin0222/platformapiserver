package com.arplanets.platform.dto.service.req;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PolicySearchData {

    private String policyName;

    private LocalDateTime createdStart;

    private LocalDateTime createdEnd;

    private LocalDateTime updatedStart;

    private LocalDateTime updatedEnd;
}
