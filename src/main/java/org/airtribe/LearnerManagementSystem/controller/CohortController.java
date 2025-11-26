package org.airtribe.LearnerManagementSystem.controller;

import org.airtribe.LearnerManagementSystem.entity.Cohort;
import org.airtribe.LearnerManagementSystem.service.CohortNotFoundException;
import org.airtribe.LearnerManagementSystem.service.LearnerManagementService;
import org.airtribe.LearnerManagementSystem.service.LearnerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CohortController {

    @Autowired
    private LearnerManagementService learnerManagementService;

    @PostMapping("/cohorts")
    public Cohort createCohort(@RequestBody Cohort cohort) {
        return learnerManagementService.createCohort(cohort);
    }

    @GetMapping("/cohorts")
    public List<Cohort> getCohorts() {
        return learnerManagementService.getAllCohorts();
    }

    @PostMapping("/assignLearnersToCohorts")
    public Cohort assignLearnersToCohorts(@RequestParam("cohortId") Long cohortId, @RequestParam("learnerId") Long learnerId) throws CohortNotFoundException, LearnerNotFoundException {
        return learnerManagementService.assignLearnersToCohort(cohortId,learnerId);
    }

    @ExceptionHandler(LearnerNotFoundException.class)
    public ResponseEntity handleLearnerNotFoundException(LearnerNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(CohortNotFoundException.class)
    public ResponseEntity handleCohortNotFoundException(Exception e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

}
//Mapping Learners to Cohorts
//Options
/**
 * /assignLearnersToCohorts?LearnerId=1&cohortId=2
 * /assignLearnersToCohorts?cohortId=2 {learnerIds: [1,2,3]}
 * /assignLearnersToCohorts?LearnerIds=1,2,3&cohortId=2
 * /assignLearnersToCohorts?cohortId=1 {learners: [{learnerName: "name1"}, {learnerName:"name2"}]}
 * */