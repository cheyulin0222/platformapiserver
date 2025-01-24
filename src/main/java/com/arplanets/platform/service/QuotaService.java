package com.arplanets.platform.service;


import com.arplanets.platform.dto.service.QuotaData;
import com.arplanets.platform.dto.service.req.QuotaCreateData;
import com.arplanets.platform.dto.service.req.QuotaUpdateData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface QuotaService {

    QuotaData get(String id);
    Page<QuotaData> getByServiceId(String serviceId, PageRequest pageRequest);
    QuotaData create(QuotaCreateData createData, String user);
    QuotaData update(QuotaUpdateData updateData, String id, String user);
    void delete(String id);
}
