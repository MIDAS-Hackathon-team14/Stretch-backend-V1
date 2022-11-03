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


    private final QueryMyWorkInfoService queryMyWorkInfoServiceService;
    private final DoPlanService doPlanService;
    private final WorkOnService workOnService;
    private final WorkOffService workOffService;
    private final QueryWorkStatusListService queryWorkStatusListService;
    private final QueryUserWorkInfoService queryWorkStatusInfoService;


    @GetMapping("/work/info")
    public WorkPlanInfoResponse queryMyWorkInfoService(){
        return queryMyWorkInfoServiceService.execute();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/work/plan")
    public void doPlan(@RequestBody @Valid DoPlanRequest request){
        doPlanService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/work/go")
    public void workGo(){
        workOnService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/work/leave")
    public void workLeave(){
        workOffService.execute();
    }

    @PostMapping("/work/status/list")
    public WorkStatusListResponse queryWorkStatusList(){
        return queryWorkStatusListService.execute();
    }

    @PostMapping("/work/info/{user-id}")
    public WorkPlanInfoResponse queryWorkStatusInfo(@PathVariable("user-id") String userId){
        return queryWorkStatusInfoService.execute(userId);
    }
}