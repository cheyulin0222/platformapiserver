package com.arplanets.platform.dto.req;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrgnizationQuotaCreateRequest {

    private String orgId;

    private String quotaId;

    private BigDecimal customValue;
}
