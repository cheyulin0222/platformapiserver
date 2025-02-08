package com.arplanets.platform.dto.service.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationQuotaUpdateData {

    private BigDecimal customValue;
}
