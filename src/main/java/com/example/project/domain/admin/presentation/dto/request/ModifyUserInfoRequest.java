package com.example.project.domain.admin.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class ModifyUserInfoRequest {

    @NotBlank(message = "name은 Null 또는 공백 또는 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 1, max = 30)
    private String name;

    @Size(max = 40)
    private String email;
}