package com.arplanets.platform.dto.service;

import com.arplanets.platform.enums.Effect;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StatementData {

    private String sid;

    private Effect effect;

    private List<String> actionIds;

    private List<String> resources;
}
