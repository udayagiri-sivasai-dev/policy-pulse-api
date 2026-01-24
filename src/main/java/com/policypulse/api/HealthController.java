package com.policypulse.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;


@RestController
public class HealthController {

    private final PolicyRepository repo;

    public HealthController(PolicyRepository repo) {
        this.repo = repo;
    }


    @GetMapping("/api/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
    @GetMapping("/api/policies")
    public  List<Policy> all() {
        return repo.findAll();
    }
}
