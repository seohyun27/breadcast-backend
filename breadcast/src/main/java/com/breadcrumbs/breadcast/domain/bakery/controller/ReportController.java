package com.breadcrumbs.breadcast.controller;

import com.breadcrumbs.breadcast.dto.report.AddReportRequest;
import com.breadcrumbs.breadcast.dto.report.ReportsResponse;
import com.breadcrumbs.breadcast.security.UserDetailsImpl;
import com.breadcrumbs.breadcast.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/bakeries/{bakeryId}/reports")
    public List<ReportsResponse> getReports(@PathVariable Long bakeryId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    @PostMapping("/bakeries/{bakeryId}/reports")
    public ResponseEntity<ReportsResponse> addReport(@PathVariable Long bakeryId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails,
                                              @RequestBody @Valid AddReportRequest request){
        return null;
    }

    @DeleteMapping("/reports/{bakeryReportId}")
    public ResponseEntity<Void> deleteBakeryReport(@PathVariable Long bakeryReportId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

}
