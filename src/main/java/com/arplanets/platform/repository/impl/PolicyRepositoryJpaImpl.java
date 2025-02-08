package com.arplanets.platform.repository.impl;

import com.arplanets.platform.bo.domain.Policy;
import com.arplanets.platform.dto.service.req.PolicySearchData;
import com.arplanets.platform.repository.PolicyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PolicyRepositoryJpaImpl implements PolicyRepository {
    @Override
    public Optional<Policy> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Page<Policy> findAll(PolicySearchData searchData, Pageable pageable) {
        return null;
    }

    @Override
    public Policy save(Policy policy) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
