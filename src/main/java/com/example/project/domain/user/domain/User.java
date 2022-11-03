package com.example.project.domain.user.domain;


import com.example.project.domain.company.domain.Company;
import com.example.project.domain.user.domain.enums.Authority;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 40, unique = true)
    private String email;

    @NotNull
    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 60, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 7, nullable = false)
    private Authority authority;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public void participateCompany(Company company) {
        this.company = company;
    }

    public void modifyInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }
}