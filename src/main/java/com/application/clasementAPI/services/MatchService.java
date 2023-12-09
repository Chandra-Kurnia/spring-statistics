package com.application.clasementAPI.services;

import com.application.clasementAPI.entities.Matchs;
import com.application.clasementAPI.entities.Status;
import com.application.clasementAPI.entities.Teams;
import com.application.clasementAPI.helpers.MessageModel;
import com.application.clasementAPI.pojos.*;
import com.application.clasementAPI.repositories.MatchsRepository;
import com.application.clasementAPI.repositories.TeamsRepository;
import com.application.clasementAPI.pojos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MatchService {
    MessageModel response = new MessageModel();
    Pagination pagination = new Pagination();

    @Autowired
    private MatchsRepository matchsRepository;
    @Autowired
    private TeamsRepository teamsRepository;

    public ResponseEntity getMatchs(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page -1, size);

            Page<Matchs> matchsPage = matchsRepository.findAll(pageable);
            List<Matchs> teams = matchsPage.getContent();
            List<StatisticMatch> statisticMatches = new ArrayList<>();

            for(Matchs team: teams) {
                StatisticMatch statisticMatch = new StatisticMatch().setStatisticMatch(team);
                statisticMatches.add(statisticMatch);
            }

            pagination.setTotalData(matchsPage.getTotalElements());
            pagination.setTotalPages(matchsPage.getTotalPages());
            pagination.setCurrentPage(matchsPage.getNumber() + 1);

            response.setResponseMessage(
                    pagination,
                    statisticMatches
            );

            return ResponseEntity.ok().body(response);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    public ResponseEntity showMatch(Long matchId) {
        try{
            Optional<Matchs> team = matchsRepository.findById(matchId);

            response.setResponseMessage(
                    null,
                    team
            );

            return ResponseEntity.ok().body(response);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    public ResponseEntity addMatch(MatchStore matchStore) {
        try {

            Teams homeTeam = teamsRepository.findById(matchStore.getHome_team()).orElse(null);
            Teams awayTeam = teamsRepository.findById(matchStore.getAway_team()).orElse(null);

            if(homeTeam == null){
                response.setStatus(true);
                response.setMessage("Home team tidak ditemukan");
                response.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            if(awayTeam == null){
                response.setStatus(true);
                response.setMessage("Away team tidak ditemukan");
                response.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            if (Objects.equals(homeTeam.getId(), awayTeam.getId())) {
                response.setStatus(true);
                response.setMessage("Home team dan Away team tidak boleh sama");
                response.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            List<Matchs> matchsHomeTeam = matchsRepository.findTeam(matchStore.getHome_team(), matchStore.getHome_team(), matchStore.getScheduled());
            List<Matchs> matchsAwayTeam = matchsRepository.findTeam(matchStore.getAway_team(), matchStore.getAway_team(), matchStore.getScheduled());

            if(!matchsHomeTeam.isEmpty()) {
                response.setStatus(false);
                response.setMessage("Home team telah memiliki pertandingan pada tanggal " + matchStore.getScheduled());
                response.setData(matchsHomeTeam);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            if(!matchsAwayTeam.isEmpty()) {
                response.setStatus(false);
                response.setMessage("Away team telah memiliki pertandingan pada tanggal " + matchStore.getScheduled());
                response.setData(matchsAwayTeam);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

//            matchsRepository.insertMatch(matchStore.getAway_team(), matchStore.getHome_team(), matchStore.getScheduled(), "SCHEDULED");

            Matchs matchs = new Matchs();

            matchs.setHomeTeam(homeTeam);
            matchs.setAwayTeam(awayTeam);
            matchs.setSchedule(matchStore.getScheduled());
            matchs.setStatus(Status.SCHEDULED);

            matchsRepository.save(matchs);

            response.setStatus(true);
            response.setMessage("Berhasil menyimpan data");
            response.setPagination(null);
            response.setData(null);

            return ResponseEntity.ok().body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    public ResponseEntity finishUpdateMatch(FinishUpdateMatch finishUpdateMatch) {
        try {
            Matchs matchsExist = matchsRepository.findById(finishUpdateMatch.getMatchId()).orElse(null);
            Teams teamHome = teamsRepository.findById(matchsExist.getHomeTeam().getId()).orElse(null);
            Teams teamAway = teamsRepository.findById(matchsExist.getAwayTeam().getId()).orElse(null);

            if(matchsExist == null) {
                response.setStatus(false);
                response.setMessage("Match dengan id " + finishUpdateMatch.getMatchId() + " Tidak ditemukan!");
                response.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            if(matchsExist.getStatus() == Status.DONE) {
                response.setStatus(false);
                response.setMessage("Match dengan id " + finishUpdateMatch.getMatchId() + " Telah selesai!");
                response.setData(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            if(finishUpdateMatch.getAwayTeamGoal() == finishUpdateMatch.getHomeTeamGoal()) {
//                Draw
                teamHome.setPoints(teamHome.getPoints()+1);
                teamHome.setDraw(teamHome.getDraw()+1);
                teamHome.setNumberOfMatch(teamHome.getNumberOfMatch()+1);
                teamHome.setHomeGoal(teamHome.getHomeGoal() + finishUpdateMatch.getHomeTeamGoal());

                teamAway.setPoints(teamAway.getPoints()+1);
                teamAway.setDraw(teamAway.getDraw()+1);
                teamAway.setNumberOfMatch(teamAway.getNumberOfMatch()+1);
                teamAway.setHomeGoal(teamAway.getAwayGoal() + finishUpdateMatch.getAwayTeamGoal());
            }else if (finishUpdateMatch.getAwayTeamGoal() > finishUpdateMatch.getHomeTeamGoal()) {
//                Away Win
                teamAway.setPoints(teamAway.getPoints()+3);
                teamAway.setWin(teamAway.getWin()+1);
                teamAway.setNumberOfMatch(teamAway.getNumberOfMatch()+1);
                teamAway.setAwayGoal(teamAway.getAwayGoal() + finishUpdateMatch.getAwayTeamGoal());

                teamHome.setLose(teamHome.getLose()+1);
                teamHome.setNumberOfMatch(teamHome.getNumberOfMatch()+1);
                teamHome.setHomeGoal(teamHome.getHomeGoal() + finishUpdateMatch.getHomeTeamGoal());
            }else {
//                Home Win
                teamHome.setPoints(teamHome.getPoints()+3);
                teamHome.setWin(teamHome.getWin()+1);
                teamHome.setNumberOfMatch(teamHome.getNumberOfMatch()+1);
                teamHome.setAwayGoal(teamHome.getAwayGoal() + finishUpdateMatch.getHomeTeamGoal());

                teamAway.setLose(teamAway.getLose()+1);
                teamAway.setNumberOfMatch(teamAway.getNumberOfMatch()+1);
                teamAway.setHomeGoal(teamAway.getHomeGoal() + finishUpdateMatch.getAwayTeamGoal());
            }

            matchsExist.setStatus(Status.DONE);

            teamsRepository.save(teamHome);
            teamsRepository.save(teamAway);
            matchsRepository.save(matchsExist);

            response.setStatus(true);
            response.setMessage("Berhasil menyimpan data");
            response.setPagination(null);
            response.setData(matchsExist);

            return ResponseEntity.ok().body(response);

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
