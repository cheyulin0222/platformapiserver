package com.arplanets.platform.bo.domain;

import com.arplanets.platform.enums.Effect;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Statement {

    private String sid;

    private Effect effect;

    // actionId list
    private List<String> actionIds;

    // arn name list
    private List<String> resources;

}
