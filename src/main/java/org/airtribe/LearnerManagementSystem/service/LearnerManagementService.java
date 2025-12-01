package org.airtribe.LearnerManagementSystem.service;

import org.airtribe.LearnerManagementSystem.entity.Cohort;
import org.airtribe.LearnerManagementSystem.entity.Learner;
import org.airtribe.LearnerManagementSystem.repository.CohortRepository;
import org.airtribe.LearnerManagementSystem.repository.Learnerrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearnerManagementService {
    @Autowired
    private Learnerrepository learnerrepository;

    @Autowired
    private CohortRepository cohortRepository;

    public Learner createLearner(Learner learner) {
        return learnerrepository.save(learner);
    }

    public List<Learner> fetchAllLearners() {
        return learnerrepository.findAll();
    }

    public Learner fetchLearnerById(Long learnerId) throws LearnerNotFoundException {
        Optional<Learner> learnerOptional = learnerrepository.findById(learnerId);
        if(learnerOptional.isPresent()) {
            return learnerOptional.get();
        }
        throw new LearnerNotFoundException("Learner not found with id:" + learnerId);
    }

    public Learner fetchLearnerByName(String learnerName) {
        return learnerrepository.findByLearnerName(learnerName);
    }

    public List<Learner> fetchLearnersComplexParams(Long learnerId, String learnerName) throws LearnerNotFoundException {
        if (learnerId != null) {
            return List.of(fetchLearnerById(learnerId));
        }

        if (learnerName != null){
            return List.of(fetchLearnerByName(learnerName));
        }

        return fetchAllLearners();
    }

    public Cohort createCohort(Cohort cohort) {
        return cohortRepository.save(cohort);
    }

    public List<Cohort> getAllCohorts() {
        return cohortRepository.findAll();
    }

    public Cohort assignLearnersToCohort(Long cohortId, Long learnerId) throws CohortNotFoundException, LearnerNotFoundException {
        Optional<Cohort> cohortOptional = cohortRepository.findById(cohortId);
        if(!cohortOptional.isPresent()) {
            throw new CohortNotFoundException("Cohort not found with id: " + cohortId);
        }
        Optional<Learner> optionalLearner = learnerrepository.findById(learnerId);
        if(!optionalLearner.isPresent()) {
            throw new LearnerNotFoundException("Learner not found with id: " + learnerId);
        }

        //If found get the cohort object and assign it to cohort
        Cohort cohort = cohortOptional.get();
        // You got cohort object basically it will contain {cohortId,cohortName,learners} right? Initially
        //existing Learners may be null but how u will get learners is by calling getter function because cohort is an object
        // refer Cohort Entity to understand this explaination
        List<Learner> existingLearners = cohort.getLearners();
        //Since existingLearners are empty at start optionalLearner.get() returns learner object so get it added like below
        existingLearners.add(optionalLearner.get());
        return cohortRepository.save(cohort);

        //Now above you have added the learners to cohort and saving what happens next is it would take the cohort
        //object and automatically maps the relation between 2 entities in the join table(COHORT_LEARNER) which means
        // as soon as you pass the learner  inside cohort object relationship is automatically created as well why?
        // because Cohort has many to many relationship with learner, did we create join table explicitly? Then who created
        //Join table? Spring data jpa created join table. So when you pass the learner object as part of cohort it is the
        //responsibility of spring data jpa to automatically put the data in relationship table as well.
    }

    public Cohort assignAndCreateLearnersToCohorts(List<Learner> learners, Long cohortId) throws CohortNotFoundException, LearnerNotFoundException {
        Optional<Cohort> cohortOptional = cohortRepository.findById(cohortId);
        if(!cohortOptional.isPresent()) {
            throw new CohortNotFoundException("Cohort not found with id: "+ cohortId);
        }
        Cohort cohort = cohortOptional.get();
        cohort.getLearners().addAll(learners);
        //learnerrepository.saveAll(learners); commented because you have written cascade in cohort entity so that learners get automatically created
        // in learner table
        return cohortRepository.save(cohort);
    }
}
