package org.airtribe.LearnerManagementSystem.service;

import org.airtribe.LearnerManagementSystem.entity.Learner;
import org.airtribe.LearnerManagementSystem.repository.Learnerrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Learner fetchLearnerById(Long learnerId) {
        return learnerrepository.getById(learnerId);
    }

    public Learner fetchLearnerByName(String learnerName) {
        return learnerrepository.findByLearnerName(learnerName);
    }

    public List<Learner> fetchLearnersComplexParams(Long learnerId, Long learnerName) {
        if(learnerId == null && learnerName == null){
            return fetchAllLearners();
        }
        if(learnerId!= null){
            return List.of(fetchLearnerById(learnerId));
        }
        return List.of(fetchLearnerById(learnerName));
    }
}
