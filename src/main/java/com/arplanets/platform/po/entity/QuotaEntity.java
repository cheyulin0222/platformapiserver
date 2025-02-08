package com.arplanets.platform.po.entity;

import com.arplanets.platform.enums.Adjustability;
import com.arplanets.platform.enums.UnitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "quota")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuotaEntity {

    @Id
    @Column(name = "quota_id")
    private String quotaId;

    @Column(name = "service_id")
    private String serviceId;

    @Column(name = "quota_name")
    private String quotaName;

    @Column(name = "unit_type")
    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @Enumerated(EnumType.STRING)
    private Adjustability adjustability;

    @Column(name = "default_value")
    private BigDecimal defaultValue;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = false, insertable = false)
    private LocalDateTime updatedAt;
}
