package com.policypulse.api;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }
    public Policy getPolicyById(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found: " + id));
    }
    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }
    public Policy updatePolicy(Long id, Policy policy) {
        Policy existingPolicy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found: " + id));

        existingPolicy.setPolicyNumber(policy.getPolicyNumber());
        existingPolicy.setHolderName(policy.getHolderName());
        existingPolicy.setStatus(policy.getStatus());
        existingPolicy.setPremium(policy.getPremium());

        return policyRepository.save(existingPolicy);
    }
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }
}