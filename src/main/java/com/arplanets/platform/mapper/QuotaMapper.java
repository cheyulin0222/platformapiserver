package com.arplanets.platform.mapper;

import com.arplanets.platform.bo.domain.Quota;
import com.arplanets.platform.dto.req.*;
import com.arplanets.platform.dto.res.*;
import com.arplanets.platform.dto.service.QuotaData;
import com.arplanets.platform.dto.service.req.QuotaCreateData;
import com.arplanets.platform.dto.service.req.QuotaUpdateData;
import com.arplanets.platform.enums.Activation;
import com.arplanets.platform.po.entity.QuotaEntity;
import org.mapstruct.Mapper;

import static com.arplanets.platform.enums.ResultMessage.CREATE_SUCCESSFUL;
import static com.arplanets.platform.enums.ResultMessage.UPDATE_SUCCESSFUL;


@Mapper(componentModel = "spring")
public interface QuotaMapper {

    // Service

    QuotaData domainToData(Quota domain);

    default Quota createDataToDomain(QuotaCreateData createData, String id, String user) {
        return Quota.builder()
                .quotaId(id)
                .serviceId(createData.getServiceId())
                .quotaName(createData.getQuotaName())
                .active(Activation.ACTIVE)
                .unitType(createData.getUnitType())
                .adjustability(createData.getAdjustability())
                .defaultValue(createData.getDefaultValue())
                .createdBy(user)
                .updatedBy(user)
                .build();
    }

    default Quota updateDataToDomain(QuotaUpdateData updateData, Quota origin, String user) {
        return Quota.builder()
                .quotaId(origin.getQuotaId())
                .serviceId(origin.getServiceId())
                .quotaName(updateData.getQuotaName() != null ? updateData.getQuotaName() : origin.getQuotaName())
                .active(origin.getActive())
                .unitType(updateData.getUnitType() != null ? updateData.getUnitType() : origin.getUnitType())
                .adjustability(updateData.getAdjustability() != null ? updateData.getAdjustability() : origin.getAdjustability())
                .defaultValue(updateData.getDefaultValue() != null ? updateData.getDefaultValue() : origin.getDefaultValue())
                .updatedBy(user)
                .build();
    }

    // Controller

    QuotaResponse dataToResponse(QuotaData so);

    QuotaCreateData requestToCreateData(QuotaCreateRequest request);

    default QuotaCreateResponse dataToCreateResponse(QuotaData data) {
        return QuotaCreateResponse.builder()
                .data(dataToResponse(data))
                .message(CREATE_SUCCESSFUL.getMessage())
                .build();
    }

    QuotaUpdateData requestToUpdateData(QuotaUpdateRequest serviceUpdateRequest);

    default QuotaUpdateResponse dataToUpdateResponse(QuotaData data) {
        return QuotaUpdateResponse.builder()
                .data(dataToResponse(data))
                .message(UPDATE_SUCCESSFUL.getMessage())
                .build();
    }

    default QuotaToggleResponse dataToToggleResponse(QuotaData data) {
        return QuotaToggleResponse.builder()
                .data(dataToResponse(data))
                .message(UPDATE_SUCCESSFUL.getMessage())
                .build();
    }


    Quota entityToDomain(QuotaEntity entity);
    QuotaEntity domainToEntity(Quota domain);
}
