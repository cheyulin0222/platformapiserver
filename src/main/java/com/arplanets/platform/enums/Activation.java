package com.arplanets.platform.enums;

import lombok.Getter;

@Getter
public enum Activation {

    ACTIVE(1),
    INACTIVE(0);

    private final Integer active;

    Activation (Integer active) {
        this.active = active;
    }
}
