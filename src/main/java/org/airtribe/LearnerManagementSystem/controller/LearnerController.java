package org.airtribe.LearnerManagementSystem.controller;

import jakarta.validation.Valid;
import org.airtribe.LearnerManagementSystem.dto.CohortDTO;
import org.airtribe.LearnerManagementSystem.dto.LearnerDTO;
import org.airtribe.LearnerManagementSystem.entity.Cohort;
import org.airtribe.LearnerManagementSystem.entity.Learner;
import org.airtribe.LearnerManagementSystem.service.LearnerManagementService;
import org.airtribe.LearnerManagementSystem.service.LearnerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LearnerController {

    @Autowired
    private LearnerManagementService learnerManagementService;

    @PostMapping("/learners")
    public Learner createLearner(@Valid  @RequestBody Learner learner){
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
    public List<LearnerDTO> fetchLearnerById(@RequestParam(value = "learnerId", required = false) Long  learnerId,
                                             @RequestParam(value = "learnerName",required = false) String learnerName) throws LearnerNotFoundException {
        List<Learner> learnerList =  learnerManagementService.fetchLearnersComplexParams(learnerId, learnerName);

        return parseLearnerListToLearnerDto(learnerList);
    }

    private List<LearnerDTO> parseLearnerListToLearnerDto(List<Learner> learnerList) {
        List<LearnerDTO> learnerDTOS = new ArrayList<>();
        for (Learner learner : learnerList) {
            LearnerDTO learnerDTO = new LearnerDTO();
            learnerDTO.setLearnerId(learner.getLearnerId());
            learnerDTO.setLearnerName(learner.getLearnerName());
            learnerDTO.setLearnerEmail(learner.getLearnerEmail());
            learnerDTO.setCohorts(new ArrayList<>());
            for(Cohort cohort : learner.getCohorts()){
                CohortDTO cohortDTO = new CohortDTO();
                cohortDTO.setCohortId(cohort.getCohortId());
                cohortDTO.setCohortName(cohort.getCohortName());
                cohortDTO.setCohortDescription(cohort.getCohortDescription());
                learnerDTO.getCohorts().add(cohortDTO);
            }
            learnerDTOS.add(learnerDTO);
        }
        return learnerDTOS;
    }

    @ExceptionHandler(LearnerNotFoundException.class)
    public ResponseEntity handleLearnerNotFoundException(LearnerNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((org.springframework.validation.FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
