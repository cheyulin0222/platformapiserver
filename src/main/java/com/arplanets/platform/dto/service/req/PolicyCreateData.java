package com.arplanets.platform.dto.service.req;

import com.arplanets.platform.enums.PolicyType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PolicyCreateData {

    private String policyName;

    private List<StatementCreateData> statements;

    private PolicyType policyType;
}
