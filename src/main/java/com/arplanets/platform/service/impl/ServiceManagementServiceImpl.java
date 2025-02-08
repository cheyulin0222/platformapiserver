package com.arplanets.platform.service.impl;

import com.arplanets.platform.enums.Activation;
import com.arplanets.platform.dto.service.req.ServiceCreateData;
import com.arplanets.platform.dto.service.req.ServiceUpdateData;
import com.arplanets.platform.dto.service.ServiceData;
import com.arplanets.platform.exception.PlatformApiException;
import com.arplanets.platform.mapper.ServiceManagementMapper;
import com.arplanets.platform.repository.ServiceRepository;
import com.arplanets.platform.service.ServiceManagementService;
import com.arplanets.platform.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.arplanets.platform.enums.Activation.ACTIVE;
import static com.arplanets.platform.exception.enums.ServiceManagementErrorCode.*;
import static com.arplanets.platform.log.enums.ServiceManagementActionType.*;

@RequiredArgsConstructor
@Transactional
@Service
public class ServiceManagementServiceImpl implements ServiceManagementService {

    private final ServiceRepository serviceRepository;
    private final ServiceManagementMapper serviceManagementMapper;
    final private static String SERVICE_PREFIX = "SERVICE";

    @Override
    public ServiceData getService(String id) {
        var option = serviceRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, GET_SERVICE);
        }

        var service = option.get();

        if (service.getDeletedAt() != null) {
            throw new PlatformApiException(_002, GET_SERVICE);
        }

        return serviceManagementMapper.domainToData(service);
    }

    @Override
    public Page<ServiceData> getAllServices(PageRequest pageRequest) {

        return serviceRepository.findAll(pageRequest)
                .map(serviceManagementMapper::domainToData);
    }

    @Override
    public ServiceData createService(ServiceCreateData request, String user) {

        String id = StringUtil.generateId(SERVICE_PREFIX);

        var service = serviceManagementMapper.createDataToDomain(request, id, user);

        service = serviceRepository.save(service);

        return serviceManagementMapper.domainToData(service);
    }

    @Override
    public ServiceData updateService(ServiceUpdateData request, String id, String user) {

        var option = serviceRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, UPDATE_SERVICE);
        }

        var service = option.get();

        if (service.getDeletedAt() != null) {
            throw new PlatformApiException(_002, UPDATE_SERVICE);
        }

        service = serviceManagementMapper.updateDataToDomain(request, option.get(), user);

        return serviceManagementMapper.domainToData(service);
    }

    @Override
    public void deleteService(String id, String user) {

        var option = serviceRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, DELETE_SERVICE);
        }

        var service = option.get();

        if (service.getDeletedAt() != null) {
            throw new PlatformApiException(_002, DELETE_SERVICE);
        }

        service.setDeletedAt(ZonedDateTime.now(ZoneId.of("Asia/Taipei")).toLocalDateTime());

        serviceRepository.save(service);

    }

    @Override
    public ServiceData recoverService(String id, String user) {

        var option = serviceRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, RECOVER_SERVICE);
        }

        var service = option.get();

        if (service.getDeletedAt() == null) {
            throw new PlatformApiException(_001, RECOVER_SERVICE);
        }

        service.setUpdatedBy(user);
        service.setDeletedAt(null);

        service = serviceRepository.save(service);

        return serviceManagementMapper.domainToData(service);
    }

    @Override
    public ServiceData toggleService(String id, Activation active, String user) {

        var option = serviceRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, TOGGLE_SERVICE);
        }

        var service = option.get();

        if (service.getDeletedAt() != null) {
            throw new PlatformApiException(_002, TOGGLE_SERVICE);
        }

        if (service.getActive() != null && service.getActive() == active) {
            if (active == ACTIVE) {
                throw new PlatformApiException(_003, TOGGLE_SERVICE);
            }

            throw new PlatformApiException(_004, TOGGLE_SERVICE);
        }

        service.setActive(active);
        service.setUpdatedBy(user);

        service = serviceRepository.save(service);

        return serviceManagementMapper.domainToData(service);

    }
}
