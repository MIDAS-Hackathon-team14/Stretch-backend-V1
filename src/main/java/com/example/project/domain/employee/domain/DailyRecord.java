package com.example.project.domain.employee.domain;

import com.example.project.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class DailyRecord {

    @Id
    @Column(name = "daily_record_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime recordStart;

    @Column(nullable = true)
    private LocalDateTime recordEnd;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void recordWorkOff(LocalDateTime now) {
        this.recordEnd = now;
    }
}