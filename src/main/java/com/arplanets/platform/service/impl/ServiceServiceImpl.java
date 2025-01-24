package com.arplanets.platform.service.impl;

import com.arplanets.platform.po.entity.ServiceEntity;
import com.arplanets.platform.dto.service.req.ServiceCreateData;
import com.arplanets.platform.dto.service.req.ServiceUpdateData;
import com.arplanets.platform.dto.service.ServiceData;
import com.arplanets.platform.exception.PlatformApiException;
import com.arplanets.platform.mapper.ServiceManagementMapper;
import com.arplanets.platform.repository.jpa.ServiceManagementJpaRepository;
import com.arplanets.platform.service.ServiceService;
import com.arplanets.platform.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.arplanets.platform.exception.enums.UserErrorCode._002;
import static com.arplanets.platform.log.enums.ServiceManagementActionType.*;

@RequiredArgsConstructor
@Transactional
@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceManagementJpaRepository serviceManagementRepository;
    private final ServiceManagementMapper serviceManagementMapper;
    final private static String SERVICE_PREFIX = "SERVICE";

    @Override
    public ServiceData getService(String id) {
        var option = serviceManagementRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, GET_SERVICE);
        }

        return serviceManagementMapper.build(option.get());
    }

    @Override
    public ServiceData createService(ServiceCreateData request, String user) {

        String id = StringUtil.generateId(SERVICE_PREFIX);

        ServiceEntity serviceEntity = serviceManagementMapper.build(request, id, user);

        serviceEntity = serviceManagementRepository.save(serviceEntity);

        return serviceManagementMapper.build(serviceEntity);
    }

    @Override
    public ServiceData updateService(ServiceUpdateData request, String id, String user) {

        var option = serviceManagementRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, UPDATE_SERVICE);
        }

        ServiceEntity serviceEntity = serviceManagementMapper.build(request, option.get(), user);

        return serviceManagementMapper.build(serviceEntity);
    }

    @Override
    public void deleteService(String id) {

        var option = serviceManagementRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, DELETE_SERVICE);
        }

        serviceManagementRepository.deleteById(id);
    }
}
