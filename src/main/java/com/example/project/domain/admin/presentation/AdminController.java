package com.example.project.domain.admin.presentation;


import com.example.project.domain.admin.presentation.dto.request.ModifyUserInfoRequest;
import com.example.project.domain.admin.service.ModifyUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final ModifyUserInfoService modifyUserInfoService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/user/{user-id}")
    public void modifyUserInfo(@PathVariable("user-id") Long userId, @RequestBody @Valid ModifyUserInfoRequest request){
        modifyUserInfoService.execute(userId, request);
    }
}