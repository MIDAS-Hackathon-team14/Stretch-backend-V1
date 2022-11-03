package com.example.project.domain.company.domain.repository;

import com.example.project.domain.company.domain.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    Optional<Company> queryByInviteCode(String inviteCode);
}