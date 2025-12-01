package org.airtribe.LearnerManagementSystem.controller;

import org.airtribe.LearnerManagementSystem.entity.Cohort;
import org.airtribe.LearnerManagementSystem.entity.Learner;
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
    //Challenges of above endpoint:
    // learner could not exist / cohort could not exist
    // Multiple mapping because for one to one it is ok but if we have 100 learners to be mappped to one cohort calls will be more
    // So steps involved are:
        //1. Create Cohort 2. Create Learner 3. Assign Learners to cohorts. Here 2 and 3 are redundant operations so they can be merged into one step

    // Here in below endpoint as name itself is self explainatory we are merging 2 and 3 steps above into one i.e.,creating and assigning learners to cohort
    // This can be called as cascading
    @PostMapping("/cohorts/{cohortId}/learners")
    public Cohort assignAndCreateLearnersToCohorts(@PathVariable("cohortId") Long cohortId, @RequestBody List<Learner> learners) throws CohortNotFoundException, LearnerNotFoundException {
        return learnerManagementService.assignAndCreateLearnersToCohorts(learners,cohortId);
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