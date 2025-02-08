package com.arplanets.platform.service.impl;

import com.arplanets.platform.bo.domain.Quota;
import com.arplanets.platform.dto.service.QuotaData;
import com.arplanets.platform.dto.service.req.QuotaCreateData;
import com.arplanets.platform.dto.service.req.QuotaUpdateData;
import com.arplanets.platform.enums.Activation;
import com.arplanets.platform.exception.PlatformApiException;
import com.arplanets.platform.mapper.QuotaMapper;
import com.arplanets.platform.repository.QuotaRepository;
import com.arplanets.platform.service.QuotaService;
import com.arplanets.platform.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.arplanets.platform.enums.Activation.ACTIVE;
import static com.arplanets.platform.exception.enums.QuotaErrorCode._002;
import static com.arplanets.platform.exception.enums.QuotaErrorCode._003;
import static com.arplanets.platform.exception.enums.QuotaErrorCode._004;
import static com.arplanets.platform.log.enums.QuotaActionType.*;

@RequiredArgsConstructor
@Transactional
@Service
public class QuotaServiceImpl implements QuotaService {

    private final QuotaRepository quotaRepository;
    private final QuotaMapper quotaMapper;
    final private static String PREFIX = "QUOTA";

    @Override
    public QuotaData getQuota(String id) {
        var option = quotaRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, GET_QUOTA);
        }

        return quotaMapper.domainToData(option.get());
    }

    @Override
    public Page<QuotaData> getQuotasByServiceId(String serviceId, PageRequest pageRequest) {

        return quotaRepository.findByServiceId(serviceId, pageRequest)
                .map(quotaMapper::domainToData);

    }

    @Override
    public QuotaData createQuota(QuotaCreateData request, String user) {
        String id = StringUtil.generateId(PREFIX);

        Quota quota = quotaMapper.createDataToDomain(request, id, user);

        quota = quotaRepository.save(quota);

        return quotaMapper.domainToData(quota);
    }

    @Override
    public QuotaData updateQuota(QuotaUpdateData request, String id, String user) {
        var option = quotaRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, UPDATE_QUOTA);
        }

        Quota quota = quotaMapper.updateDataToDomain(request, option.get(), user);

        return quotaMapper.domainToData(quota);
    }

    @Override
    public QuotaData toggleQuota(String id, Activation active, String user) {
        var option = quotaRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, TOGGLE_QUOTA);
        }

        var quota = option.get();

        if (quota.getActive() != null && quota.getActive() == active) {
            if (active == ACTIVE) {
                throw new PlatformApiException(_003, TOGGLE_QUOTA);
            }

            throw new PlatformApiException(_004, TOGGLE_QUOTA);
        }

        quota.setActive(active);
        quota.setUpdatedBy(user);

        quota = quotaRepository.save(quota);

        return quotaMapper.domainToData(quota);
    }


    @Override
    public void deleteQuota(String id) {
        var option = quotaRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, DELETE_QUOTA);
        }

        quotaRepository.deleteById(id);
    }
}
