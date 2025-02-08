package com.arplanets.platform.bo.domain;

import com.arplanets.platform.enums.Effect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Statement {

    private String sid;

    private Effect effect;

    // actionId list
    private List<String> actionIds;

    // arn name list
    private List<String> resources;

}
