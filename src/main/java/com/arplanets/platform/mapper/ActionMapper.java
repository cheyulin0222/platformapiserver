package com.arplanets.platform.mapper;

import com.arplanets.platform.bo.domain.Action;
import com.arplanets.platform.dto.service.ActionData;
import com.arplanets.platform.dto.service.req.ActionCreateData;
import com.arplanets.platform.dto.service.req.ActionUpdateData;
import com.arplanets.platform.enums.ResultMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActionMapper {

    // Service
    ActionData domainToData(Action domain);

    default Action createDataToDomain(ActionCreateData createData, String id, String user, String prefix) {
        return Action.builder()
                .actionId(id)
                .actionName(genActionName(prefix, createData.getTempActionName()))
                .serviceId(createData.getServiceId())
                .createdBy(user)
                .updatedBy(user)
                .build();
    }

    default Action updateDataToDomain(ActionUpdateData updateData, Action origin, String user, String prefix) {
        return Action.builder()
                .actionId(origin.getActionId())
                .actionName(updateData.getTempActionName() != null ? genActionName(prefix, updateData.getTempActionName()) : origin.getActionName())
                .serviceId(origin.getServiceId())
                .updatedBy(user)
                .build();
    }


    // Repository
    Action entityToDomain(ActionEntity entity);
    ActionEntity domainToEntity(Action domain);



    // Controller
    ActionCreateData requestToSo(ActionCreateRequest request);
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

    default String genActionName(String prefix, String tempActionName) {
        return prefix + ":" + tempActionName;
    }
}
