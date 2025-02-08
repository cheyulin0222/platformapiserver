package com.arplanets.platform.repository.impl;

import com.arplanets.platform.bo.domain.OrganizationQuota;
import com.arplanets.platform.repository.OrganizationQuotaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrganizationQuotaRepositoryJpaImp implements OrganizationQuotaRepository {
    @Override
    public Optional<OrganizationQuota> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<OrganizationQuota> findOrgIdAndQuotaIds(String orgId, List<String> quotaIds) {
        return List.of();
    }

    @Override
    public OrganizationQuota save(OrganizationQuota organizationQuota) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
