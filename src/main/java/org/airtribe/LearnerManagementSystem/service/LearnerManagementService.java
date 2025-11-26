package org.airtribe.LearnerManagementSystem.service;

import org.airtribe.LearnerManagementSystem.entity.Learner;
import org.airtribe.LearnerManagementSystem.repository.Learnerrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearnerManagementService {
    @Autowired
    private Learnerrepository learnerrepository;

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
}
