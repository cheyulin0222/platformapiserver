package com.arplanets.platform.mapper;

import com.arplanets.platform.bo.domain.Policy;
import com.arplanets.platform.bo.domain.Statement;
import com.arplanets.platform.dto.service.PolicyData;
import com.arplanets.platform.dto.service.req.*;

import java.util.List;

public interface PolicyMapper {

    PolicyData domainToData(Policy policy);

    default Policy createDataToDomain(PolicyCreateData createData, String id, String user) {
        return Policy.builder()
                .policyId(id)
                .policyName(createData.getPolicyName())
                .statements(createDataToDomain(createData.getStatements()))
                .policyType(createData.getPolicyType())
                .createdBy(user)
                .updatedBy(user)
                .build();
    }



    default Policy updateDataToDomain(PolicyUpdateData updateData, Policy origin, String user) {
        return Policy.builder()
                .policyId(origin.getPolicyId())
                .policyName(updateData.getPolicyName() != null ? updateData.getPolicyName() : origin.getPolicyName())
                .statements(updateData.getStatements() != null ? updateDataToDomain(updateData.getStatements()) : origin.getStatements())
                .policyType(origin.getPolicyType())
                .updatedBy(user)
                .build();
    }

    List<Statement> createDataToDomain(List<StatementCreateData> createData);
    List<Statement> updateDataToDomain(List<StatementUpdateData> updateData);
}
