package com.arplanets.platform.service;

import com.arplanets.platform.dto.service.req.ServiceCreateData;
import com.arplanets.platform.dto.service.req.ServiceUpdateData;
import com.arplanets.platform.dto.service.ServiceData;
import com.arplanets.platform.enums.Activation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ServiceManagementService {

    ServiceData getService(String id);
    Page<ServiceData> getAllServices(PageRequest pageRequest);
    ServiceData createService(ServiceCreateData request, String user);
    ServiceData updateService(ServiceUpdateData request, String id, String user);
    void deleteService(String id, String user);
    ServiceData recoverService(String id, String user);
    ServiceData toggleService(String id, Activation activation, String user);
}
