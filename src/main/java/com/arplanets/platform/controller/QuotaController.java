package com.arplanets.platform.controller;

import com.arplanets.platform.dto.req.QuotaCreateRequest;
import com.arplanets.platform.dto.req.QuotaUpdateRequest;
import com.arplanets.platform.dto.res.QuotaCreateResponse;
import com.arplanets.platform.dto.res.QuotaDeleteResponse;
import com.arplanets.platform.dto.res.QuotaResponse;
import com.arplanets.platform.dto.res.QuotaUpdateResponse;
import com.arplanets.platform.dto.service.req.QuotaCreateData;
import com.arplanets.platform.dto.service.req.QuotaUpdateData;
import com.arplanets.platform.mapper.QuotaMapper;
import com.arplanets.platform.service.QuotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@RequestMapping("/api/quotas")
@RequiredArgsConstructor
@Tag(name = "Quotas (服務額度) API")
public class QuotaController {

    private final QuotaService quotaService;
    private final QuotaMapper quotaMapper;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "查詢服務額度", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<QuotaResponse> get(
            @PathVariable
            String id
    ) {

        var result = quotaService.get(id);

        return ResponseEntity.ok(quotaMapper.soToResponse(result));
    }

    @GetMapping(value = "/service/{serviceId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "以 Service ID 查詢服務額度", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Page<QuotaResponse>> getByServiceID(
            @PathVariable
            String serviceId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(defaultValue = "quotaName") String[] sortBy
    ) {

        PageRequest pageRequest = PageRequest.of(page, size, direction, sortBy);
        var result = quotaService.getByServiceId(serviceId, pageRequest)
                        .map(quotaMapper::soToResponse);

        return ResponseEntity.ok(result);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "新增服務額度", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<QuotaCreateResponse> createService(@RequestBody @Valid QuotaCreateRequest request, Authentication authentication) {

        QuotaCreateData reqSO = quotaMapper.requestToSo(request);

        var result = quotaService.create(reqSO, authentication.getName());

        return ResponseEntity.ok(quotaMapper.soToCreateResponse(result, CREATE_SUCCESSFUL));
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "修改服務額度", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<QuotaUpdateResponse> updateService(
            @PathVariable
            String id,
            @RequestBody @Valid QuotaUpdateRequest request,
            Authentication authentication
    ) {

        QuotaUpdateData reqSO = quotaMapper.requestToSo(request);

        var result = quotaService.update(reqSO, id, authentication.getName());

        return ResponseEntity.ok(quotaMapper.soToUpdateResponse(result, UPDATE_SUCCESSFUL));
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "刪除服務", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<QuotaDeleteResponse> deleteService(
            @PathVariable

            String id
    ) {

        quotaService.delete(id);

        var result = QuotaDeleteResponse.builder()
                .message(DELETE_SUCCESSFUL.getMessage())
                .build();

        return ResponseEntity.ok(result);
    }
}
