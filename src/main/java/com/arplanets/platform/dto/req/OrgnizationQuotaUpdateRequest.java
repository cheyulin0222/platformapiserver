package com.arplanets.platform.dto.req;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrgnizationQuotaUpdateRequest {

    private BigDecimal customValue;
}
