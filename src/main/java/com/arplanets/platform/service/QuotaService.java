package com.arplanets.platform.service;


import com.arplanets.platform.dto.service.QuotaData;
import com.arplanets.platform.dto.service.req.QuotaCreateData;
import com.arplanets.platform.dto.service.req.QuotaUpdateData;
import com.arplanets.platform.enums.Activation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface QuotaService {

    QuotaData getQuota(String id);
    Page<QuotaData> getQuotasByServiceId(String serviceId, PageRequest pageRequest);
    QuotaData createQuota(QuotaCreateData createData, String user);
    QuotaData updateQuota(QuotaUpdateData updateData, String id, String user);
    QuotaData toggleQuota(String id, Activation active, String user);
    void deleteQuota(String id);
}
