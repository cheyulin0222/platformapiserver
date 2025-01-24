package com.arplanets.platform.service.impl;

import com.arplanets.platform.bo.domain.Quota;
import com.arplanets.platform.dto.service.QuotaData;
import com.arplanets.platform.dto.service.req.QuotaCreateData;
import com.arplanets.platform.dto.service.req.QuotaUpdateData;
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

import static com.arplanets.platform.exception.enums.QuotaErrorCode._002;
import static com.arplanets.platform.log.enums.QuotaActionType.*;

@RequiredArgsConstructor
@Transactional
@Service
public class QuotaServiceImpl implements QuotaService {

    private final QuotaRepository quotaRepository;
    private final QuotaMapper quotaMapper;
    final private static String PREFIX = "QUOTA";

    @Override
    public QuotaData get(String id) {
        var option = quotaRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, GET_QUOTA);
        }

        return quotaMapper.domainToSo(option.get());
    }

    @Override
    public Page<QuotaData> getByServiceId(String serviceId, PageRequest pageRequest) {
        return quotaRepository.findByServiceId(serviceId, pageRequest)
                .map(quotaMapper::domainToSo);

    }

    @Override
    public QuotaData create(QuotaCreateData request, String user) {
        String id = StringUtil.generateId(PREFIX);

        Quota quota = quotaMapper.soToDomain(request, id, user);

        quota = quotaRepository.save(quota);

        return quotaMapper.domainToSo(quota);
    }

    @Override
    public QuotaData update(QuotaUpdateData request, String id, String user) {
        var option = quotaRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, UPDATE_QUOTA);
        }

        Quota quota = quotaMapper.soToDomain(request, option.get(), user);

        return quotaMapper.domainToSo(quota);
    }

    @Override
    public void delete(String id) {
        var option = quotaRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, DELETE_QUOTA);
        }

        quotaRepository.deleteById(id);
    }
}
