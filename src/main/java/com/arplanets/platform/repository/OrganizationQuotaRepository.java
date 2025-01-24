package com.arplanets.platform.repository;

import com.arplanets.platform.bo.domain.OrganizationQuota;

import java.util.List;
import java.util.Optional;

public interface OrganizationQuotaRepository {

    Optional<OrganizationQuota> findById(String id);
    List<OrganizationQuota> findOrgIdAndQuotaIds(String orgId, List<String> quotaIds);
    OrganizationQuota save(OrganizationQuota organizationQuota);
    void deleteById(String id);


}
