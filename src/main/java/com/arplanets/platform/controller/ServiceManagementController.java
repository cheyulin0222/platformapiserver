package com.arplanets.platform.controller;

import com.arplanets.platform.dto.req.ServiceCreateRequest;
import com.arplanets.platform.dto.req.ServiceUpdateRequest;
import com.arplanets.platform.dto.res.*;
import com.arplanets.platform.dto.req.ServiceToggleRequest;
import com.arplanets.platform.dto.service.req.ServiceCreateData;
import com.arplanets.platform.dto.service.req.ServiceUpdateData;
import com.arplanets.platform.mapper.ServiceManagementMapper;
import com.arplanets.platform.service.ServiceManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static com.arplanets.platform.enums.ResultMessage.*;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
@Tag(name = "Services (服務) API")
public class ServiceManagementController {

    private final ServiceManagementService serviceManagementService;
    private final ServiceManagementMapper serviceManagementMapper;

    private static final String SERVICE_ID_PATTERN = "^SERVICE-" +
            "(20\\d{2})" +                  // 年份 (2000-2099)
            "(0[1-9]|1[0-2])" +             // 月份 (01-12)
            "(0[1-9]|[12]\\d|3[01])" +      // 日期 (01-31)
            "([01]\\d|2[0-3])" +            // 小時 (00-23)
            "([0-5]\\d)" +                  // 分鐘 (00-59)
            "([0-5]\\d)" +                  // 秒鐘 (00-59)
            "-[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";

    private static final String SERVICE_ID_MESSAGE = "服務 ID 格式不正確";

    private static final String SERVICE_ID_DESCRITION = "服務 ID (格式：SERVICE-yyyyMMddHHmmss-UUID)";

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "查詢服務", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ServiceResponse> getService(
        @PathVariable
        @Pattern(regexp = SERVICE_ID_PATTERN, message = SERVICE_ID_MESSAGE)
        @Parameter(description = SERVICE_ID_DESCRITION)
        String id
    ) {

        var result = serviceManagementService.getService(id);

        return ResponseEntity.ok(serviceManagementMapper.dataToResponse(result));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "查詢所有服務", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Page<ServiceResponse>> getAllServices(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "ASC") Sort.Direction direction,
        @RequestParam(defaultValue = "serviceName") String[] sortBy
    ) {

        PageRequest pageRequest = PageRequest.of(page, size, direction, sortBy);

        var result = serviceManagementService.getAllServices(pageRequest)
                        .map(serviceManagementMapper::dataToResponse);

        return ResponseEntity.ok(result);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "新增服務", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ServiceCreateResponse> createService(@RequestBody @Valid ServiceCreateRequest request, Authentication authentication) {

        ServiceCreateData serviceCreateData = serviceManagementMapper.requestToCreateData(request);

        var result = serviceManagementService.createService(serviceCreateData, authentication.getName());

        return ResponseEntity.ok(serviceManagementMapper.dataToCreateResponse(result));
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "修改服務", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ServiceUpdateResponse> updateService(
            @PathVariable
            @Pattern(regexp = SERVICE_ID_PATTERN, message = SERVICE_ID_MESSAGE)
            @Parameter(description = SERVICE_ID_DESCRITION)
            String id,
            @RequestBody @Valid ServiceUpdateRequest request,
            Authentication authentication
    ) {

        ServiceUpdateData serviceUpdateData = serviceManagementMapper.requestToUpdateData(request);

        var result = serviceManagementService.updateService(serviceUpdateData, id, authentication.getName());

        return ResponseEntity.ok(serviceManagementMapper.dataToUpdateResponse(result));
    }

    @PutMapping(value = "/{id}/toggle", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "修改服務啟用狀態", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ServiceToggleResponse> toggleService(
            @PathVariable
            @Pattern(regexp = SERVICE_ID_PATTERN, message = SERVICE_ID_MESSAGE)
            @Parameter(description = SERVICE_ID_DESCRITION)
            String id,
            @RequestBody @Valid ServiceToggleRequest request,
            Authentication authentication
    ) {

        var result = serviceManagementService.toggleService(id, request.getActive(), authentication.getName());

        return ResponseEntity.ok(serviceManagementMapper.dataToToggleResponse(result));
    }

    @PutMapping(value = "/{id}/recover", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "還原服務", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ServiceRecoverResponse> recoverService (
            @PathVariable
            @Pattern(regexp = SERVICE_ID_PATTERN, message = SERVICE_ID_MESSAGE)
            @Parameter(description = SERVICE_ID_DESCRITION)
            String id,
            Authentication authentication
    ) {

        var result = serviceManagementService.recoverService(id, authentication.getName());

        return ResponseEntity.ok(serviceManagementMapper.dataToRecoverResponse(result));
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "刪除服務", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<ServiceDeleteResponse> deleteService(
        @PathVariable
        @Pattern(regexp = SERVICE_ID_PATTERN, message = SERVICE_ID_MESSAGE)
        @Parameter(description = SERVICE_ID_DESCRITION)
        String id,
        Authentication authentication
    ) {

        serviceManagementService.deleteService(id, authentication.getName());

        var result = ServiceDeleteResponse.builder()
                .message(DELETE_SUCCESSFUL.getMessage())
                .build();

        return ResponseEntity.ok(result);
    }


}
