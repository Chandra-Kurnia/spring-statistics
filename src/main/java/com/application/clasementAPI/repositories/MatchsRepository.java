package com.application.clasementAPI.repositories;


import com.application.clasementAPI.entities.Matchs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchsRepository extends JpaRepository <Matchs, Long> {
    @Query(value = "SELECT * FROM matchs m where (m.away_team_id = :awayTeamId or m.home_team_id = :homeTeamId) and schedule = :date", nativeQuery = true)
    List<Matchs> findTeam(@Param("awayTeamId") Long awayTeamId, @Param("homeTeamId") Long homeTeamId, @Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO matchs (away_team_id, home_team_id, schedule, status) VALUES (:awayTeamId, :homeTeamId, :schedule, :status)", nativeQuery = true)
    void insertMatch(@Param("awayTeamId") Long awayTeamId, @Param("homeTeamId") Long homeTeamId, @Param("schedule") LocalDate schedule, @Param("status") String status);
}
