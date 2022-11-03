package com.example.project.domain.company.domain;

import com.example.project.domain.company.domain.enums.FlexTime;
import com.example.project.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Company {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    FlexTime flexTime;

    @Column(nullable = false)
    private Boolean smartWork;

    @Column(nullable = false)
    private Boolean home;

    @OneToMany(mappedBy = "company")
    private List<User> employeeList;

    @Column(nullable = false)
    private String inviteCode;

}