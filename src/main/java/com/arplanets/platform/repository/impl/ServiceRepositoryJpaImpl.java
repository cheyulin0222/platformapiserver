package com.arplanets.platform.repository.impl;

import com.arplanets.platform.bo.domain.Service;
import com.arplanets.platform.mapper.ServiceManagementMapper;
import com.arplanets.platform.repository.ServiceRepository;
import com.arplanets.platform.repository.jpa.ServiceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ServiceRepositoryJpaImpl implements ServiceRepository {

    private final ServiceJpaRepository repository;
    private final ServiceManagementMapper mapper;

    @Override
    public Optional<Service> findById(String id) {
        return repository.findById(id)
                    .map(mapper::entityToDomain);
    }

    @Override
    public Page<Service> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::entityToDomain);
    }

    @Override
    public Service save(Service service) {
        var result = repository.save(mapper.domainToEntity(service));
        return mapper.entityToDomain(result);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
