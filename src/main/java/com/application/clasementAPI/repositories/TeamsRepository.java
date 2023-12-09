package com.application.clasementAPI.repositories;

import com.application.clasementAPI.entities.Teams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamsRepository extends JpaRepository <Teams, Long> {
    @Query(value = "SELECT team_id as id, team_name as name, city FROM teams", countQuery = "SELECT COUNT(*) FROM teams", nativeQuery = true)
    Page<Object[]> teams(Pageable pageable);

    @Query(value = "SELECT *, (SELECT COUNT(*) FROM teams AS t2 WHERE t2.points >= t1.points) AS rank from teams AS t1 ORDER BY points DESC, ABS(home_goal - away_goal) DESC, away_goal DESC, number_of_match ASC", countQuery = "SELECT COUNT(*) FROM teams", nativeQuery = true)
    Page<Object[]> getClasements(Pageable pageable);
}
