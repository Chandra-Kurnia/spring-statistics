package com.application.clasementAPI.services;

import com.application.clasementAPI.helpers.MessageModel;
import com.application.clasementAPI.pojos.ListClasement;
import com.application.clasementAPI.pojos.Pagination;
import com.application.clasementAPI.repositories.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClasementService {
    MessageModel response = new MessageModel();
    Pagination pagination = new Pagination();
    @Autowired
    private TeamsRepository teamsRepository;

    public ResponseEntity getClasements(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page -1, size);
            Page<Object[]> clasementPage = teamsRepository.getClasements(pageable);
            List<Object[]> clasementObject = clasementPage.getContent();
            List<ListClasement> clasements = new ArrayList<>();

            for (Object[] clasement : clasementObject) {
                ListClasement dataClasement = new ListClasement().setListClasement(clasement);
                clasements.add(dataClasement);
            }

            pagination.setTotalData(clasementPage.getTotalElements());
            pagination.setTotalPages(clasementPage.getTotalPages());
            pagination.setCurrentPage(clasementPage.getNumber() + 1);
//
            response.setResponseMessage(
                    pagination,
                    clasements
            );

            return ResponseEntity.ok().body(response);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
