package com.arplanets.platform.repository.jpa;

import com.arplanets.platform.po.entity.QuotaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface QuotaJpaRepository extends JpaRepository<QuotaEntity, String> {

    Page<QuotaEntity> findByServiceId(String serviceId, Pageable pageable);

}
