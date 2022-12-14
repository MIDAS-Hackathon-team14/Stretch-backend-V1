package com.example.project.domain.employee.presentation;

import com.example.project.domain.employee.presentation.dto.request.DoPlanRequest;
import com.example.project.domain.employee.presentation.dto.response.WorkPlanInfoResponse;
import com.example.project.domain.employee.presentation.dto.response.WorkStatusListResponse;
import com.example.project.domain.employee.service.DoPlanService;
import com.example.project.domain.employee.service.QueryMyWorkInfoService;
import com.example.project.domain.employee.service.QueryUserWorkInfoService;
import com.example.project.domain.employee.service.QueryWorkStatusListService;
import com.example.project.domain.employee.service.WorkOffService;
import com.example.project.domain.employee.service.WorkOnService;
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
@RequestMapping("/employees")
@RestController
public class EmployeeController {

    private final QueryMyWorkInfoService queryMyWorkInfoService;
    private final DoPlanService doPlanService;
    private final WorkOnService workOnService;
    private final WorkOffService workOffService;
    private final QueryWorkStatusListService queryWorkStatusListService;
    private final QueryUserWorkInfoService queryUserWorkInfoService;


    @GetMapping("/work/info")
    public WorkPlanInfoResponse queryMyWorkInfoService(){
        return queryMyWorkInfoService.execute();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/work/plan")
    public void doPlan(@RequestBody @Valid DoPlanRequest request){
        doPlanService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/work/go")
    public void workGo(){
        workOnService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/work/leave")
    public void workLeave(){
        workOffService.execute();
    }

    @GetMapping("/work/status/list")
    public WorkStatusListResponse queryWorkStatusList(){
        return queryWorkStatusListService.execute();
    }

    @GetMapping("/work/info/{user-id}")
    public WorkPlanInfoResponse queryUserWorkInfo(@PathVariable("user-id") Long userId){
        return queryUserWorkInfoService.execute(userId);
    }
}