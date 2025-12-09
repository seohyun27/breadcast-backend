package com.breadcrumbs.breadcast.domain.bakery.controller;

import com.breadcrumbs.breadcast.domain.bakery.dto.report.AddReportRequest;
import com.breadcrumbs.breadcast.domain.bakery.dto.report.ReportsResponse;
import com.breadcrumbs.breadcast.domain.bakery.service.ReportService;
import com.breadcrumbs.breadcast.global.apiPayload.ApiResponse;
import com.breadcrumbs.breadcast.global.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/bakeries/{bakeryId}/reports")
    public ApiResponse<List<ReportsResponse>> getReports(@PathVariable Long bakeryId,
                                                         @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @PostMapping("/bakeries/{bakeryId}/reports")
    public ApiResponse<ReportsResponse> addReport(@PathVariable Long bakeryId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails,
                                              @RequestBody @Valid AddReportRequest request){
        Long userId = (userDetails != null) ? userDetails.getUserId() : null;
        ReportsResponse response = reportService.addReport(bakeryId, userId, request);
        return ApiResponse.onSuccess("제보 작성에 성공하였습니다.", response);
    }

    @DeleteMapping("/reports/{bakeryReportId}")
    public ApiResponse<Void> deleteBakeryReport(@PathVariable Long bakeryReportId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

}
