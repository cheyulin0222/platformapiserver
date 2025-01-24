package com.arplanets.platform.service;

import com.arplanets.platform.dto.service.PolicyData;
import com.arplanets.platform.dto.service.req.PolicyCreateData;
import com.arplanets.platform.dto.service.req.PolicySearchData;
import com.arplanets.platform.dto.service.req.PolicyUpdateData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PolicyService {

    PolicyData get(String id);
    Page<PolicyData> search(PolicySearchData searchData, PageRequest pageRequest);
    PolicyData create(PolicyCreateData createData, String user);
    PolicyData update(PolicyUpdateData updateData, String id, String user);
    void delete(String id);
}
