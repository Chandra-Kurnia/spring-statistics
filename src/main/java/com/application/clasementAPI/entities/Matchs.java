package com.application.clasementAPI.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "matchs")
public class Matchs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "home_team_id", referencedColumnName = "team_id")
//    @JsonBackReference
    private Teams homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", referencedColumnName = "team_id")
//    @JsonBackReference
    private Teams awayTeam;

    @Column(name = "schedule")
    private LocalDate schedule;

//    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updatedAt;

}
