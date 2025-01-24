package com.arplanets.platform.service;

import com.arplanets.platform.dto.service.req.ServiceCreateData;
import com.arplanets.platform.dto.service.req.ServiceUpdateData;
import com.arplanets.platform.dto.service.ServiceData;

public interface ServiceService {

    ServiceData getService(String id);
    ServiceData createService(ServiceCreateData request, String user);
    ServiceData updateService(ServiceUpdateData request, String id, String user);
    void deleteService(String id);
}
