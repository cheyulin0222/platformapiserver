package com.arplanets.platform.dto.service.req;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrganizationQuotaUpdateData {

    private BigDecimal customValue;
}
