package com.arplanets.platform.repository;

import com.arplanets.platform.bo.domain.Policy;
import com.arplanets.platform.dto.service.PolicyData;
import com.arplanets.platform.dto.service.req.PolicySearchData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PolicyRepository {

    Optional<Policy> findById(String id);
    Page<Policy> findAll(PolicySearchData searchData, Pageable pageable);
    Policy save(Policy policy);
    void deleteById(String id);
}
