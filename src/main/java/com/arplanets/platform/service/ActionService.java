package com.arplanets.platform.service;

import com.arplanets.platform.dto.service.ActionData;
import com.arplanets.platform.dto.service.req.ActionCreateData;
import com.arplanets.platform.dto.service.req.ActionUpdateData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ActionService {

    ActionData get(String id);
    Page<ActionData> getByServiceId(String serviceId, PageRequest pageRequest);
    ActionData create(ActionCreateData createData, String user);
    ActionData update(ActionUpdateData updateData, String id, String user);
    void delete(String id);
}
