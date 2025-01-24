package com.arplanets.platform.mapper;

import com.arplanets.platform.po.entity.ServiceEntity;
import com.arplanets.platform.dto.req.ServiceActivateRequest;
import com.arplanets.platform.dto.req.ServiceCreateRequest;
import com.arplanets.platform.dto.req.ServiceUpdateRequest;
import com.arplanets.platform.dto.res.ServiceActivateResponse;
import com.arplanets.platform.dto.res.ServiceResponse;
import com.arplanets.platform.dto.res.ServiceCreateResponse;
import com.arplanets.platform.dto.res.ServiceUpdateResponse;
import com.arplanets.platform.dto.service.req.ServiceCreateData;
import com.arplanets.platform.dto.service.req.ServiceUpdateData;
import com.arplanets.platform.dto.service.ServiceData;
import com.arplanets.platform.enums.Activation;
import com.arplanets.platform.enums.ResultMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceManagementMapper {

    // Get
    ServiceData build(ServiceEntity serviceEntity);
    ServiceResponse build(ServiceData serviceData);

    // Create
    ServiceCreateData build(ServiceCreateRequest serviceCreateRequest);
    default ServiceEntity build(ServiceCreateData request, String id, String user) {
        return ServiceEntity.builder()
                .serviceId(id)
                .serviceName(request.getServiceName())
                .prefix(request.getPrefix())
                .active(Activation.ACTIVE)
                .createdBy(user)
                .updatedBy(user)
                .build();


    }
    default ServiceCreateResponse build(ServiceData serviceData, ResultMessage resultMessage) {
        return ServiceCreateResponse.builder()
                .serviceInfo(build(serviceData))
                .message(resultMessage.getMessage())
                .build();
    }

    // Update
    ServiceUpdateData build(ServiceUpdateRequest serviceUpdateRequest);
    ServiceUpdateData build(ServiceActivateRequest serviceActivateRequest);
    default ServiceEntity build(ServiceUpdateData request, ServiceEntity origin, String user) {
        return ServiceEntity.builder()
                .serviceId(origin.getServiceId())
                .serviceName(request.getServiceName() != null ? request.getServiceName() : origin.getServiceName())
                .prefix(request.getPrefix() != null ? request.getPrefix() : origin.getPrefix())
                .active(request.getActive() != null ? request.getActive() : origin.getActive())
                .updatedBy(user)
                .build();
    }
    default ServiceUpdateResponse buildServiceUpdateResponse(ServiceData serviceData, ResultMessage resultMessage) {
        return ServiceUpdateResponse.builder()
                .serviceInfo(build(serviceData))
                .message(resultMessage.getMessage())
                .build();
    }
    default ServiceActivateResponse buildServiceActivateResponse(ServiceData serviceData, ResultMessage resultMessage) {
        return ServiceActivateResponse.builder()
                .serviceInfo(build(serviceData))
                .message(resultMessage.getMessage())
                .build();
    }

}
