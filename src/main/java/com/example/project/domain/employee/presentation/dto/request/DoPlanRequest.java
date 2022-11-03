package com.example.project.domain.employee.presentation.dto.request;

import com.example.project.domain.company.domain.enums.FlexPlace;
import com.example.project.domain.employee.domain.enums.OutOfOfficeType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class DoPlanRequest {
    @NotNull(message = "is_out_of_office는 null이어선 안됩니다")
    private Boolean isOutOfOffice;

    private OutOfOfficeType outOfOfficeType;

    private FlexPlace place;

    private LocalDateTime planStart;

    private LocalDateTime planEnd;
}