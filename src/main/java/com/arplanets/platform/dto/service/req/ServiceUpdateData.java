package com.arplanets.platform.dto.service.req;

import com.arplanets.platform.enums.Activation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceUpdateData {

    private String serviceName;

    private String prefix;

    private Activation Active;
}
