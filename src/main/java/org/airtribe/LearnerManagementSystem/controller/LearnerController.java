package org.airtribe.LearnerManagementSystem.controller;

import org.airtribe.LearnerManagementSystem.entity.Learner;
import org.airtribe.LearnerManagementSystem.service.LearnerManagementService;
import org.airtribe.LearnerManagementSystem.service.LearnerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LearnerController {

    @Autowired
    private LearnerManagementService learnerManagementService;

    @PostMapping("/learners")
    public Learner createLearner(@RequestBody Learner learner){
        return learnerManagementService.createLearner(learner);
    }

//    @GetMapping("/learners")
//    public List<Learner> getAllLearners(){
//        return learnerManagementService.fetchAllLearners();
//    }
//    @GetMapping("/learners/{learnerId}")
//    public Learner fetchLearnerById(@PathVariable("learnerId") Long learnerId){
//        return learnerManagementService.fetchLearnerById(learnerId);
//    }
//    @GetMapping("/learners/{learnerName}")
//    public Learner fetchLearnerByName(@PathVariable("learnerName") String learnerName){
//        return learnerManagementService.fetchLearnerByName(learnerName);
//    }
    @GetMapping("/learners")
    public List<Learner> fetchLearnerById(@RequestParam(value = "learnerId", required = false) Long  learnerId,
                                          @RequestParam(value = "learnerName",required = false) String learnerName) throws LearnerNotFoundException {
        return learnerManagementService.fetchLearnersComplexParams(learnerId, learnerName);
    }

    @ExceptionHandler(LearnerNotFoundException.class)
    public ResponseEntity handleLearnerNotFoundException(LearnerNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
