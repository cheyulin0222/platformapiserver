package com.arplanets.platform.dto.service.req;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PolicyUpdateData {

    private String policyName;

    private List<StatementUpdateData> statements;
}
