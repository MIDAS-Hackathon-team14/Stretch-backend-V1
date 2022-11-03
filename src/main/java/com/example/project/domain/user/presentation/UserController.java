package com.example.project.domain.user.presentation;


import com.example.project.domain.user.presentation.dto.request.LoginRequest;
import com.example.project.domain.user.presentation.dto.request.SignUpRequest;
import com.example.project.domain.user.presentation.dto.response.TokenResponse;
import com.example.project.domain.user.presentation.dto.response.UserInfoResponse;
import com.example.project.domain.user.service.LoginService;
import com.example.project.domain.user.service.QueryMyInfoService;
import com.example.project.domain.user.service.QueryUserInfoService;
import com.example.project.domain.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final LoginService loginService;
    private final SignUpService signUpService;
    private final QueryMyInfoService queryMyInfoService;
    private final QueryUserInfoService queryUserInfoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public TokenResponse signUp(@RequestBody @Valid SignUpRequest request){
        return signUpService.execute(request);
    }

    @PostMapping("/auth")
    public TokenResponse login(@RequestBody @Valid LoginRequest request){
        return loginService.execute(request);
    }

    @GetMapping("")
    public UserInfoResponse queryMyInfo(){
        return queryMyInfoService.execute();
    }

    @GetMapping("/{user-id}")
    public UserInfoResponse queryUserInfo(@PathVariable("user-id") Long userId){
        return queryUserInfoService.execute(userId);
    }

}