package com.arplanets.platform.dto.service.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCreateData {

    private String serviceName;

    private String prefix;

}
