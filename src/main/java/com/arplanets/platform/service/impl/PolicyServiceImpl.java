package com.arplanets.platform.service.impl;

import com.arplanets.platform.bo.domain.Policy;
import com.arplanets.platform.dto.service.PolicyData;
import com.arplanets.platform.dto.service.req.PolicyCreateData;
import com.arplanets.platform.dto.service.req.PolicySearchData;
import com.arplanets.platform.dto.service.req.PolicyUpdateData;
import com.arplanets.platform.exception.PlatformApiException;
import com.arplanets.platform.mapper.PolicyMapper;
import com.arplanets.platform.repository.PolicyRepository;
import com.arplanets.platform.service.PolicyService;
import com.arplanets.platform.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.arplanets.platform.exception.enums.PolicyErrorCode._002;
import static com.arplanets.platform.log.enums.PolicyActionType.*;

@RequiredArgsConstructor
@Transactional
@Service
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
    private final PolicyMapper policyMapper;
    final private static String PREFIX = "POLICY";

    @Override
    public PolicyData get(String id) {
        var option = policyRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, GET_POLICY);
        }

        return policyMapper.domainToData(option.get());
    }

    @Override
    public Page<PolicyData> search(PolicySearchData searchData, PageRequest pageRequest) {
        return policyRepository.findAll(searchData, pageRequest)
                .map(policyMapper::domainToData);
    }

    @Override
    public PolicyData create(PolicyCreateData createData, String user) {
        String id = StringUtil.generateId(PREFIX);


        Policy policy = policyMapper.createDataToDomain(createData, id, user);

        policy = policyRepository.save(policy);

        return policyMapper.domainToData(policy);
    }

    @Override
    public PolicyData update(PolicyUpdateData updateData, String id, String user) {
        var option = policyRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, UPDATE_POLICY);
        }

        Policy policy = policyMapper.updateDataToDomain(updateData, option.get(), user);

        return policyMapper.domainToData(policy);
    }

    @Override
    public void delete(String id) {
        var option = policyRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, DELETE_POLICY);
        }

        policyRepository.deleteById(id);
    }
}
