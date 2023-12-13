package com.ecommerce.backend.services;

import com.ecommerce.backend.models.JWTResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JWTResponseService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public JWTResponse saveOrUpdateJWTResponse(JWTResponse jwtResponse) {
        // Check if JWTResponse exists for the given username
        TypedQuery<JWTResponse> query = entityManager.createQuery(
                "SELECT j FROM JWTResponse j WHERE j.username = :username", JWTResponse.class);
        query.setParameter("username", jwtResponse.getUsername());
        List<JWTResponse> existingResults = query.getResultList();

        if (!existingResults.isEmpty()) {
            // Update existing JWTResponse
            JWTResponse existingJWTResponse = existingResults.get(0);
            existingJWTResponse.setJwtToken(jwtResponse.getJwtToken());
            // Other fields update if necessary

            entityManager.merge(existingJWTResponse);
            return existingJWTResponse;
        } else {
            // Save new JWTResponse
            entityManager.persist(jwtResponse);
            return jwtResponse;
        }
    }
}
