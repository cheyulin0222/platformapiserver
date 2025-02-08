package com.arplanets.platform.repository;

import com.arplanets.platform.bo.domain.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ServiceRepository {

    Optional<Service> findById(String id);
    Page<Service> findAll(Pageable pageable);
    Service save(Service service);
    void deleteById(String id);
}
