package org.airtribe.LearnerManagementSystem.controller;

import org.airtribe.LearnerManagementSystem.entity.Learner;
import org.airtribe.LearnerManagementSystem.service.LearnerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
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
                                          @RequestParam(value = "learnerName",required = false) Long learnerName){
        return learnerManagementService.fetchLearnersComplexParams(learnerId, learnerName);
    }
}
