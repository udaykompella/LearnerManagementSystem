package org.airtribe.LearnerManagementSystem.controller;

import org.airtribe.LearnerManagementSystem.entity.Cohort;
import org.airtribe.LearnerManagementSystem.service.LearnerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class V1Controller {
    @Autowired
    private LearnerManagementService learnerManagementService;
    @GetMapping("/cohorts")
    public Page<Cohort> getCohorts(@RequestParam(value = "pageNumber", required=false) Integer pageNumber,
                                   @RequestParam(value = "pageSize", required=false) Integer pageSize,
                                   @RequestParam(value = "sortBy", required=false) String sortBy,
                                   @RequestParam(value = "sortDir", required=false) String sortDir
                                   ) {
        if(pageNumber < 0){
            pageNumber = 0;
        }
        if(pageNumber > 2000){
            pageSize = 10;
        }
        if(!(sortDir.equals("asc") || sortDir.equals("asc"))){
            sortDir = "asc";
        }
        return learnerManagementService.fetchPaginatedCohorts(pageSize, pageNumber,sortBy,sortDir);
    }
}
