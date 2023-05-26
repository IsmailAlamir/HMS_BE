package com.example.hospitalManagement.HMS.service;

import com.example.hospitalManagement.HMS.DTO.VisitDTO;
import com.example.hospitalManagement.HMS.Domain.Visit;
import org.springframework.stereotype.Service;

@Service
public class VisitService {

    public VisitDTO convertToVisitDTO(Visit visit) {
        VisitDTO visitDTO = new VisitDTO();
        visitDTO.setId(visit.getId());
        visitDTO.setSummary(visit.getSummary());
        visitDTO.setDescription(visit.getDescription());

        return visitDTO;
    }
}