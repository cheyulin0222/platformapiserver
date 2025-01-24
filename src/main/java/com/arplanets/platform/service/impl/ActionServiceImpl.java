package com.arplanets.platform.service.impl;

import com.arplanets.platform.bo.domain.Action;
import com.arplanets.platform.dto.service.ActionData;
import com.arplanets.platform.dto.service.ServiceData;
import com.arplanets.platform.dto.service.req.ActionCreateData;
import com.arplanets.platform.dto.service.req.ActionUpdateData;
import com.arplanets.platform.exception.PlatformApiException;
import com.arplanets.platform.mapper.ActionMapper;
import com.arplanets.platform.repository.ActionRepository;
import com.arplanets.platform.service.ActionService;
import com.arplanets.platform.service.ServiceService;
import com.arplanets.platform.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.arplanets.platform.exception.enums.ActionErrorCode._002;
import static com.arplanets.platform.log.enums.ActionActionType.*;

@RequiredArgsConstructor
@Transactional
@Service
public class ActionServiceImpl implements ActionService {

    private final ServiceService serviceService;
    private final ActionRepository actionRepository;
    private final ActionMapper actionMapper;
    final private static String PREFIX = "ACTION";

    @Override
    public ActionData get(String id) {
        var option = actionRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, GET_ACTION);
        }

        return actionMapper.domainToData(option.get());
    }

    @Override
    public Page<ActionData> getByServiceId(String serviceId, PageRequest pageRequest) {
        return actionRepository.findByServiceId(serviceId, pageRequest)
                .map(actionMapper::domainToData);
    }

    @Override
    public ActionData create(ActionCreateData createData, String user) {
        String id = StringUtil.generateId(PREFIX);

        ServiceData serviceData = serviceService.getService(createData.getServiceId());
        String prefix = serviceData.getPrefix();

        Action action = actionMapper.createDataToDomain(createData, id, user, prefix);

        action = actionRepository.save(action);

        return actionMapper.domainToData(action);
    }

    @Override
    public ActionData update(ActionUpdateData updateData, String id, String user) {
        var option = actionRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, UPDATE_ACTION);
        }

        ServiceData serviceData = serviceService.getService(option.get().getServiceId());
        String prefix = serviceData.getPrefix();

        Action action = actionMapper.updateDataToDomain(updateData, option.get(), user, prefix);

        return actionMapper.domainToData(action);
    }

    @Override
    public void delete(String id) {
        var option = actionRepository.findById(id);

        if (option.isEmpty()) {
            throw new PlatformApiException(_002, DELETE_ACTION);
        }

        actionRepository.deleteById(id);
    }
}
