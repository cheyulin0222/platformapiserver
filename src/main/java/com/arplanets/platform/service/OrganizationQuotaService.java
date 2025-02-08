package com.arplanets.platform.service;

import com.arplanets.platform.bo.domain.OrganizationQuota;
import com.arplanets.platform.dto.service.req.OrganizationQuotaCreateData;
import com.arplanets.platform.dto.service.OrganizationQuotaData;
import com.arplanets.platform.dto.service.req.OrganizationQuotaUpdateData;

import java.util.List;

public interface OrganizationQuotaService {

    OrganizationQuotaData getOrganizationQuota(String id);
    List<OrganizationQuota> getByOrgIdAndQuotaIds(String orgId, List<String> quotaIds);
    OrganizationQuotaData create(OrganizationQuotaCreateData createData, String user);
    OrganizationQuotaData update(OrganizationQuotaUpdateData updateData, String id, String user);
    void delete(String id);

}
