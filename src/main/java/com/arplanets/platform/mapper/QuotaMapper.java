package com.arplanets.platform.mapper;

import com.arplanets.platform.bo.domain.Quota;
import com.arplanets.platform.dto.req.*;
import com.arplanets.platform.dto.res.*;
import com.arplanets.platform.dto.service.QuotaData;
import com.arplanets.platform.dto.service.req.QuotaCreateData;
import com.arplanets.platform.dto.service.req.QuotaUpdateData;
import com.arplanets.platform.enums.ResultMessage;
import com.arplanets.platform.po.entity.QuotaEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface QuotaMapper {

    // Get
    default QuotaData domainToSo(Quota domain) {
        return QuotaData.builder()
                .quotaId(domain.getQuotaId())
                .serviceId(domain.getServiceId())
                .quotaName(domain.getQuotaName())
                .unitType(domain.getUnitType())
                .adjustability(domain.getAdjustability())
                .defaultValues(domain.getDefaultValues())
                .createdBy(domain.getCreatedBy())
                .updatedBy(domain.getUpdatedBy())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }
    QuotaResponse soToResponse(QuotaData so);
    Quota entityToDomain(QuotaEntity entity);
    QuotaEntity domainToEntity(Quota domain);



    // Create
    QuotaCreateData requestToSo(QuotaCreateRequest request);
    default Quota soToDomain(QuotaCreateData request, String id, String user) {
        return Quota.builder()
                .quotaId(id)
                .serviceId(request.getServiceId())
                .quotaName(request.getQuotaName())
                .unitType(request.getUnitType())
                .adjustability(request.getAdjustability())
                .defaultValues(request.getDefaultValues())
                .createdBy(user)
                .updatedBy(user)
                .build();
    }
    default QuotaCreateResponse soToCreateResponse(QuotaData so, ResultMessage resultMessage) {
        return QuotaCreateResponse.builder()
                .data(soToResponse(so))
                .message(resultMessage.getMessage())
                .build();
    }

    // Update
    QuotaUpdateData requestToSo(QuotaUpdateRequest serviceUpdateRequest);
    default Quota soToDomain(QuotaUpdateData request, Quota origin, String user) {
        return Quota.builder()
                .quotaId(origin.getQuotaId())
                .serviceId(origin.getServiceId())
                .quotaName(request.getQuotaName() != null ? request.getQuotaName() : origin.getQuotaName())
                .unitType(request.getUnitType() != null ? request.getUnitType() : origin.getUnitType())
                .adjustability(request.getAdjustability() != null ? request.getAdjustability() : origin.getAdjustability())
                .defaultValues(request.getDefaultValues() != null ? request.getDefaultValues() : origin.getDefaultValues())
                .updatedBy(user)
                .build();
    }
    default QuotaUpdateResponse soToUpdateResponse(QuotaData quotaData, ResultMessage resultMessage) {
        return QuotaUpdateResponse.builder()
                .data(soToResponse(quotaData))
                .message(resultMessage.getMessage())
                .build();
    }
}
