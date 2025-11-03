package com.breadcrumbs.breadcast.controller;

import com.breadcrumbs.breadcast.dto.report.AddReportRequest;
import com.breadcrumbs.breadcast.dto.report.ReportsResponse;
import com.breadcrumbs.breadcast.security.UserDetailsImpl;
import com.breadcrumbs.breadcast.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    public List<ReportsResponse> getReports(@PathVariable Long bakeryId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

    public ResponseEntity<ReportsResponse> addReport(@PathVariable Long bakeryId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails,
                                              @RequestBody @Valid AddReportRequest request){
        return null;
    }

    public ResponseEntity<Void> deleteBakeryReport(@PathVariable Long bakeryReportId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails){
        return null;
    }

}
