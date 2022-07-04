package sharma.sahil.learning.java.article.backend.api;

import sharma.sahil.learning.java.article.backend.dto.StatsResponse;
import sharma.sahil.learning.java.article.backend.service.DashboardService;
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
