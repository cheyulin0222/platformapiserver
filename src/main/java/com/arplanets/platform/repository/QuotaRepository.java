package com.arplanets.platform.repository;

import com.arplanets.platform.bo.domain.Quota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface QuotaRepository {

    Optional<Quota> findById(String id);
    Page<Quota> findByServiceId(String serviceId, Pageable pageable);
    Quota save(Quota quota);
    void deleteById(String id);
}
