package com.application.clasementAPI.repositories;

import com.application.clasementAPI.entities.Teams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamsRepository extends JpaRepository <Teams, Long> {
    @Query(value = "SELECT team_id as id, team_name as name, city FROM teams", countQuery = "SELECT COUNT(*) FROM teams",
            nativeQuery = true)
    Page<Object[]> teams(Pageable pageable);

    @Query(value = "SELECT *, " +
            "CASE " +
            "WHEN number_of_match = 0 THEN null " +
            "ELSE DENSE_RANK() OVER (ORDER BY points DESC,  number_of_match DESC) " +
            "END AS rank " +
            "from teams AS t1 " +
//            "ORDER BY points DESC, ABS(home_goal - away_goal) DESC, away_goal DESC, number_of_match ASC",
            "ORDER BY " +
            "CASE " +
            "WHEN rank != 0 THEN 0 " +
            "ELSE 1 " +
            "END, " +
            "rank ASC",
            countQuery = "SELECT COUNT(*) FROM teams", nativeQuery = true)
    Page<Object[]> getClasements(Pageable pageable);
}
