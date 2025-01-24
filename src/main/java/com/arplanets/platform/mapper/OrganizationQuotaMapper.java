package com.arplanets.platform.mapper;

import com.arplanets.platform.bo.domain.OrganizationQuota;
import com.arplanets.platform.dto.req.OrgnizationQuotaCreateRequest;
import com.arplanets.platform.dto.req.OrgnizationQuotaUpdateRequest;
import com.arplanets.platform.dto.res.OrganizationQuotaCreateResponse;
import com.arplanets.platform.dto.res.OrganizationQuotaResponse;
import com.arplanets.platform.dto.res.OrganizationQuotaUpdateResponse;
import com.arplanets.platform.dto.service.OrganizationQuotaData;
import com.arplanets.platform.dto.service.QuotaData;
import com.arplanets.platform.dto.service.req.OrganizationQuotaCreateData;
import com.arplanets.platform.dto.service.req.OrganizationQuotaUpdateData;
import com.arplanets.platform.enums.AccountLevel;
import com.arplanets.platform.enums.ResultMessage;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface OrganizationQuotaMapper {

    default OrganizationQuotaResponse quotaDataToResponse(QuotaData quotaData, Map<String, OrganizationQuota> orgQuotaMap, AccountLevel level) {

        OrganizationQuota orgQuota = orgQuotaMap.get(quotaData.getQuotaId());
        Map<AccountLevel, BigDecimal> defaultValues = quotaData.getDefaultValues();

        return OrganizationQuotaResponse.builder()
                .orgQuotaId(orgQuota != null ? orgQuota.getOrgQuotaId() : null)
                .quotaId(quotaData.getQuotaId())
                .quotaName(quotaData.getQuotaName())
                .unitType(quotaData.getUnitType())
                .adjustability(quotaData.getAdjustability())
                // 之後要判斷並降級
                .defaultValue(defaultValues != null ? defaultValues.get(level) : null)
                .customValue(orgQuota != null && orgQuota.getCustomValue() != null ?
                        orgQuota.getCustomValue() :
                        (defaultValues != null ? defaultValues.get(level) : null))
                .createdBy(orgQuota != null ? orgQuota.getCreatedBy() : null)
                .updatedBy(orgQuota != null ? orgQuota.getUpdatedBy() : null)
                .createdAt(orgQuota != null ? orgQuota.getCreatedAt() : null)
                .updatedAt(orgQuota != null ? orgQuota.getUpdatedAt() : null)
                .build();
    }

    OrganizationQuotaCreateData requestToCreateData(OrgnizationQuotaCreateRequest request);

    default OrganizationQuotaResponse dataToResponse(OrganizationQuotaData orgQuotaData, QuotaData quotaData, AccountLevel level) {
        return OrganizationQuotaResponse.builder()
                .orgQuotaId(orgQuotaData.getOrgQuotaId())
                .quotaId(orgQuotaData.getQuotaId())
                .quotaName(quotaData.getQuotaName())
                .unitType(quotaData.getUnitType())
                .adjustability(quotaData.getAdjustability())
                // 之後要判斷並降級
                .defaultValue(quotaData.getDefaultValues() != null ? quotaData.getDefaultValues().get(level) : null)
                .customValue(orgQuotaData.getCustomValue())
                .createdBy(orgQuotaData.getCreatedBy())
                .updatedBy(orgQuotaData.getUpdatedBy())
                .createdAt(orgQuotaData.getCreatedAt())
                .updatedAt(orgQuotaData.getUpdatedAt())
                .build();
    }

    default OrganizationQuotaCreateResponse buildCreateResponse(OrganizationQuotaResponse result, ResultMessage resultMessage) {
        return OrganizationQuotaCreateResponse.builder()
                .data(result)
                .message(resultMessage.getMessage())
                .build();
    }

    default OrganizationQuotaUpdateResponse buildUpdateResponse(OrganizationQuotaResponse result, ResultMessage resultMessage) {
        return null;
    }

    OrganizationQuotaUpdateData requestToUpdateData(OrgnizationQuotaUpdateRequest request);

    OrganizationQuotaData domainToData(OrganizationQuota organizationQuota);

    default OrganizationQuota createDataToDomain(OrganizationQuotaCreateData createData, String id, String user) {
        return OrganizationQuota.builder()
                .orgQuotaId(id)
                .orgId(createData.getOrgId())
                .quotaId(createData.getQuotaId())
                .customValue(createData.getCustomValue())
                .createdBy(user)
                .updatedBy(user)
                .build();
    }

    default OrganizationQuota updateDataToDomain(OrganizationQuotaUpdateData updateData, OrganizationQuota origin, String user) {
        return OrganizationQuota.builder()
                .orgQuotaId(origin.getOrgQuotaId())
                .orgId(origin.getOrgId())
                .quotaId(origin.getQuotaId())
                .customValue(updateData.getCustomValue())
                .updatedBy(user)
                .build();
    }
}
