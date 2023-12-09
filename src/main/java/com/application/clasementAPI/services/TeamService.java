package com.application.clasementAPI.services;

import com.application.clasementAPI.entities.Teams;
import com.application.clasementAPI.helpers.MessageModel;
import com.application.clasementAPI.pojos.ListTeams;
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
import java.util.Optional;

@Service
public class TeamService {
    MessageModel response = new MessageModel();
    Pagination pagination = new Pagination();

    @Autowired
    private TeamsRepository teamsRepository;

    public ResponseEntity getTeams(int page, int size) {
        try {

            Pageable pageable = PageRequest.of(page -1, size);
//
//            Page<Teams> teamPage = teamsRepository.findAll(pageable);
//            List<Teams> teams = teamPage.getContent();

            Page<Object[]> teamPage = teamsRepository.teams(pageable);
            List<Object[]> teamObject = teamPage.getContent();
            List<ListTeams> teams = new ArrayList<>();

            for (Object[] data : teamObject) {
                ListTeams listTeams = new ListTeams().setListTeams(data);
                teams.add(listTeams);
            }

            pagination.setTotalData(teamPage.getTotalElements());
            pagination.setTotalPages(teamPage.getTotalPages());
            pagination.setCurrentPage(teamPage.getNumber() + 1);

            response.setResponseMessage(
                    pagination,
                    teams
            );

            return ResponseEntity.ok().body(response);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    public ResponseEntity showTeam(Long teamId) {
        try{

            Optional<Teams> team = teamsRepository.findById(teamId);

            response.setResponseMessage(
                    null,
                    team
            );

            return ResponseEntity.ok().body(response);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
