package com.arplanets.platform.controller;

import com.arplanets.platform.bo.domain.OrganizationQuota;
import com.arplanets.platform.dto.req.OrgnizationQuotaCreateRequest;
import com.arplanets.platform.dto.req.OrgnizationQuotaUpdateRequest;
import com.arplanets.platform.dto.res.*;
import com.arplanets.platform.dto.service.OrganizationQuotaData;
import com.arplanets.platform.dto.service.QuotaData;
import com.arplanets.platform.dto.service.req.OrganizationQuotaCreateData;
import com.arplanets.platform.dto.service.req.OrganizationQuotaUpdateData;
import com.arplanets.platform.enums.AccountLevel;
import com.arplanets.platform.mapper.OrganizationQuotaMapper;
import com.arplanets.platform.service.OrganizationQuotaService;
import com.arplanets.platform.service.OrganizationService;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.arplanets.platform.enums.ResultMessage.*;

@RestController
@RequestMapping("/api/org-quotas")
@RequiredArgsConstructor
@Tag(name = "Organization Quotas (組織服務額度) API")
public class OrganizationQuotaController {

    private final OrganizationService orgService;
    private final QuotaService quotaService;
    private final OrganizationQuotaService organizationQuotaService;
    private final OrganizationQuotaMapper orgQuotaMapper;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "依服務查詢所有組織服務額度", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Page<OrganizationQuotaResponse>> getByOrgIdAndServiceID(
            @RequestParam String orgId,
            @RequestParam String serviceId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(defaultValue = "quotaName") String[] sortBy
    ) {

        PageRequest pageRequest = PageRequest.of(page, size, direction, sortBy);

        // 取得 Service 下的所有 Quota
        Page<QuotaData> quotaPage  = quotaService.getQuotasByServiceId(serviceId, pageRequest);

        // 取得 Service 下的所有 QuotaId
        List<String> quotaIds = quotaPage.map(QuotaData::getQuotaId).getContent();

        // 依前面的 QuotaIds 取得 Org 所有有修改過的 quota
        List<OrganizationQuota> orgQuotas = organizationQuotaService.getByOrgIdAndQuotaIds(orgId, quotaIds);

        // 將有修改過資料的取代 default 資料
        Map<String, OrganizationQuota> orgQuotaMap = orgQuotas.stream()
                .collect(Collectors.toMap(
                        OrganizationQuota::getQuotaId,
                        quota -> quota
                ));

        // 取得組織的 account level
        AccountLevel level = orgService.get(orgId).getLevel();

        // 依照 account level 返回確切的 value
        var result = quotaPage.map(quotaData ->
            orgQuotaMapper.quotaDataToResponse(quotaData, orgQuotaMap, level)
        );

        return ResponseEntity.ok(result);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "新增組織服務額度", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<OrganizationQuotaCreateResponse> createService(@RequestBody @Valid OrgnizationQuotaCreateRequest request, Authentication authentication) {

        OrganizationQuotaCreateData createDate = orgQuotaMapper.requestToCreateData(request);

        OrganizationQuotaData orgQuotaData = organizationQuotaService.create(createDate, authentication.getName());

        QuotaData quotaData = quotaService.getQuota(orgQuotaData.getQuotaId());
        AccountLevel level = orgService.get(orgQuotaData.getOrgId()).getLevel();

        var result = orgQuotaMapper.dataToResponse(orgQuotaData, quotaData, level);

        return ResponseEntity.ok(orgQuotaMapper.buildCreateResponse(result, CREATE_SUCCESSFUL));
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "修改組織服務額度", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<OrganizationQuotaUpdateResponse> updateService(
            @PathVariable
            String id,
            @RequestBody @Valid OrgnizationQuotaUpdateRequest request,
            Authentication authentication
    ) {

        OrganizationQuotaUpdateData updateData = orgQuotaMapper.requestToUpdateData(request);

        OrganizationQuotaData orgQuotaData = organizationQuotaService.update(updateData, id, authentication.getName());

        QuotaData quotaData = quotaService.getQuota(orgQuotaData.getQuotaId());
        AccountLevel level = orgService.get(orgQuotaData.getOrgId()).getLevel();

        var result = orgQuotaMapper.dataToResponse(orgQuotaData, quotaData, level);

        return ResponseEntity.ok(orgQuotaMapper.buildUpdateResponse(result, UPDATE_SUCCESSFUL));
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "刪除組織服務額度", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<QuotaDeleteResponse> deleteService(
            @PathVariable
            String id
    ) {

        quotaService.deleteQuota(id);

        var result = QuotaDeleteResponse.builder()
                .message(DELETE_SUCCESSFUL.getMessage())
                .build();

        return ResponseEntity.ok(result);
    }
}
