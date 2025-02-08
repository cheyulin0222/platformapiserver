package com.arplanets.platform.mapper;

import com.arplanets.platform.bo.domain.Service;
import com.arplanets.platform.dto.res.*;
import com.arplanets.platform.po.entity.ServiceEntity;
import com.arplanets.platform.dto.req.ServiceCreateRequest;
import com.arplanets.platform.dto.req.ServiceUpdateRequest;
import com.arplanets.platform.dto.service.req.ServiceCreateData;
import com.arplanets.platform.dto.service.req.ServiceUpdateData;
import com.arplanets.platform.dto.service.ServiceData;
import com.arplanets.platform.enums.Activation;
import org.mapstruct.Mapper;

import static com.arplanets.platform.enums.ResultMessage.*;

@Mapper(componentModel = "spring")
public interface ServiceManagementMapper {


    // Service

    ServiceData domainToData(Service domain);

    default Service createDataToDomain(ServiceCreateData createData, String id, String user) {
        return Service.builder()
                .serviceId(id)
                .serviceName(createData.getServiceName())
                .prefix(createData.getPrefix())
                .active(Activation.ACTIVE)
                .createdBy(user)
                .updatedBy(user)
                .build();
    }

    default Service updateDataToDomain(ServiceUpdateData updateData, Service origin, String user) {
        return Service.builder()
                .serviceId(origin.getServiceId())
                .serviceName(updateData.getServiceName() != null ? updateData.getServiceName() : origin.getServiceName())
                .prefix(updateData.getPrefix() != null ? updateData.getPrefix() : origin.getPrefix())
                .active(origin.getActive())
                .updatedBy(user)
                .build();
    }


    // Controller

    ServiceResponse dataToResponse(ServiceData data);

    ServiceCreateData requestToCreateData(ServiceCreateRequest createRequest);

    default ServiceCreateResponse dataToCreateResponse(ServiceData data) {
        return ServiceCreateResponse.builder()
                .data(dataToResponse(data))
                .message(CREATE_SUCCESSFUL.getMessage())
                .build();
    }

    ServiceUpdateData requestToUpdateData(ServiceUpdateRequest updateRequest);


    default ServiceUpdateResponse dataToUpdateResponse(ServiceData data) {
        return ServiceUpdateResponse.builder()
                .data(dataToResponse(data))
                .message(UPDATE_SUCCESSFUL.getMessage())
                .build();
    }
    default ServiceToggleResponse dataToToggleResponse(ServiceData data) {
        return ServiceToggleResponse.builder()
                .data(dataToResponse(data))
                .message(UPDATE_SUCCESSFUL.getMessage())
                .build();
    }

    default ServiceRecoverResponse dataToRecoverResponse(ServiceData data) {
        return ServiceRecoverResponse.builder()
                .data(dataToResponse(data))
                .message(RECOVER_SUCCESSFUL.getMessage())
                .build();
    }


    // Repository

    Service entityToDomain(ServiceEntity entity);
    ServiceEntity domainToEntity(Service domain);

}
