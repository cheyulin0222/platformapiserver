package com.arplanets.platform.repository.impl;

import com.arplanets.platform.bo.domain.Quota;
import com.arplanets.platform.mapper.QuotaMapper;
import com.arplanets.platform.po.entity.QuotaEntity;
import com.arplanets.platform.repository.QuotaRepository;
import com.arplanets.platform.repository.jpa.QuotaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuotaRespositoryJpaImpl implements QuotaRepository {

    private final QuotaJpaRepository repository;
    private final QuotaMapper quotaMapper;

    @Override
    public Optional<Quota> findById(String id) {
        return repository.findById(id)
                .map(quotaMapper::entityToDomain);
    }

    @Override
    public Page<Quota> findByServiceId(String serviceId, Pageable pageable) {
        return repository.findByServiceId(serviceId, pageable)
                .map(quotaMapper::entityToDomain);
    }

    @Override
    public Quota save(Quota quota) {
        QuotaEntity result = repository.save(quotaMapper.domainToEntity(quota));
        return quotaMapper.entityToDomain(result);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
