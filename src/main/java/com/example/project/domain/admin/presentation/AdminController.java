package com.example.project.domain.admin.presentation;

import com.example.project.domain.admin.service.AdminQueryWorkStatusInfoService;
import com.example.project.domain.admin.service.AdminQueryWorkStatusListService;
import com.example.project.domain.admin.service.ModifyUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminQueryWorkStatusListService queryWorkStatusListService;
    private final AdminQueryWorkStatusInfoService queryWorkStatusInfoService;
    private final ModifyUserInfoService modifyUserInfoService;


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/user/{user-id}")
    public void modifyUserInfo(@PathVariable("user-id") Long userId){
        return modifyUserInfoService.execute(userId);
    }
}