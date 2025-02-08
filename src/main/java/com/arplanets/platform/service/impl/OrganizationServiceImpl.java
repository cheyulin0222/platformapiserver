package com.arplanets.platform.service.impl;

import com.arplanets.platform.dto.service.OrganizationData;
import com.arplanets.platform.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
    @Override
    public OrganizationData get(String id) {
        return null;
    }
}
