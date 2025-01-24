package com.arplanets.platform.repository;

import com.arplanets.platform.bo.domain.Action;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ActionRepository {

    Optional<Action> findById(String id);
    Page<Action> findByServiceId(String serviceId, Pageable pageable);
    Action save(Action quota);
    void deleteById(String id);
}
