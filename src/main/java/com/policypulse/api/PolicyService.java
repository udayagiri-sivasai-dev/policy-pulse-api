package com.policypulse.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@Service
public class PolicyService {

    private final PolicyRepository policyRepository;

    private final S3Service s3Service;

    public PolicyService(PolicyRepository policyRepository, S3Service s3Service) {
        this.policyRepository = policyRepository;
        this.s3Service = s3Service;
    }
    public Policy getPolicyById(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Policy not found: " + id));
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
    public void deletePolicy(Long id) {
        Policy existingPolicy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found: " + id));

        policyRepository.delete(existingPolicy);
    }
    public Page<Policy> getAllPolicies(int page, int size) {
        return policyRepository.findAll(PageRequest.of(page, size));
    }
    public Page<Policy> getPoliciesByStatus(String status, int page, int size) {
        return policyRepository.findByStatusIgnoreCase(status, PageRequest.of(page, size));
    }
    public Policy uploadPolicyDocument(Long id, MultipartFile file) throws IOException {
        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found: " + id));

        String documentKey = s3Service.uploadFile(file);
        policy.setDocumentKey(documentKey);

        return policyRepository.save(policy);
    }
}