package com.arplanets.platform.service.impl;

import com.arplanets.platform.bo.domain.OrganizationQuota;
import com.arplanets.platform.dto.service.OrganizationQuotaData;
import com.arplanets.platform.dto.service.req.OrganizationQuotaCreateData;
import com.arplanets.platform.dto.service.req.OrganizationQuotaUpdateData;
import com.arplanets.platform.exception.PlatformApiException;
import com.arplanets.platform.mapper.OrganizationQuotaMapper;
import com.arplanets.platform.repository.OrganizationQuotaRepository;
import com.arplanets.platform.service.OrganizationQuotaService;
import com.arplanets.platform.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.arplanets.platform.exception.enums.OrganizationQuotaErrorCode._002;
import static com.arplanets.platform.log.enums.OrganizationQuotaActionType.*;

@RequiredArgsConstructor
@Transactional
@Service
public class OrganizationQuotaServiceImpl implements OrganizationQuotaService {

    private final OrganizationQuotaRepository organizationQuotaRepository;
    private final OrganizationQuotaMapper organizationQuotaMapper;
    final private static String PREFIX = "ORGANIZATION-QUOTA";

    @Override
    public OrganizationQuotaData get(String id) {
        var option = organizationQuotaRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, GET_ORGANIZATION_QUOTA);
        }

        return organizationQuotaMapper.domainToData(option.get());
    }

    @Override
    public List<OrganizationQuota> getByOrgIdAndQuotaIds(String orgId, List<String> quotaIds) {
        return organizationQuotaRepository.findOrgIdAndQuotaIds(orgId, quotaIds);
    }


    @Override
    public OrganizationQuotaData create(OrganizationQuotaCreateData createData, String user) {
        String id = StringUtil.generateId(PREFIX);

        OrganizationQuota domain = organizationQuotaMapper.createDataToDomain(createData, id, user);

        domain = organizationQuotaRepository.save(domain);

        return organizationQuotaMapper.domainToData(domain);
    }

    @Override
    public OrganizationQuotaData update(OrganizationQuotaUpdateData updateData, String id, String user) {
        var option = organizationQuotaRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, UPDATE_ORGANIZATION_QUOTA);
        }

        OrganizationQuota domain = organizationQuotaMapper.updateDataToDomain(updateData, option.get(), user);

        return organizationQuotaMapper.domainToData(domain);
    }

    @Override
    public void delete(String id) {
        var option = organizationQuotaRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, DELETE_ORGANIZATION_QUOTA);
        }

        organizationQuotaRepository.deleteById(id);
    }
}
