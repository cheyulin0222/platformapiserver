package com.arplanets.platform.repository.jpa;

import com.arplanets.platform.po.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceJpaRepository extends JpaRepository<ServiceEntity, String> {
}
