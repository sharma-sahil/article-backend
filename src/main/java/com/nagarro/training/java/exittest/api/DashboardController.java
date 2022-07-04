package com.nagarro.training.java.exittest.api;

import com.nagarro.training.java.exittest.dto.StatsResponse;
import com.nagarro.training.java.exittest.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/public/stats")
    public StatsResponse getApplicationStats() {
        return this.dashboardService.getApplicationStats();
    }
}
