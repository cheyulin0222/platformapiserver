package com.arplanets.platform.repository.impl;

import com.arplanets.platform.bo.domain.Action;
import com.arplanets.platform.repository.ActionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ActionRepositoryJpaImpl implements ActionRepository {
    @Override
    public Optional<Action> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Page<Action> findByServiceId(String serviceId, Pageable pageable) {
        return null;
    }

    @Override
    public Action save(Action quota) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
