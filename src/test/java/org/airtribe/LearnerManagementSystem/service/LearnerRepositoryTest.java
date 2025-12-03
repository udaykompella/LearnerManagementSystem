package org.airtribe.LearnerManagementSystem.service;

import org.airtribe.LearnerManagementSystem.entity.Learner;
import org.airtribe.LearnerManagementSystem.repository.Learnerrepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LearnerRepositoryTest {

    @Autowired
    private Learnerrepository learnerrepository;

    private Learner learner;

    @BeforeEach
    public void cleanUp(){
        learner = new Learner("test","test");
        learnerrepository.deleteAll();
    }

    @Test
    public void saveLearner(){
        Learner savedLearner = learnerrepository.save(learner);
        assert(savedLearner.getLearnerId() != null);
        assert(savedLearner.getLearnerName().equals("test"));
        assert(savedLearner.getLearnerEmail().equals("test"));
    }

    @Test
    public void testFetchLearnerById(){
        Learner savedLearner = learnerrepository.save(learner);
        Optional<Learner> learnerOptional = learnerrepository.findById(savedLearner.getLearnerId());
        assert(learnerOptional.isPresent());
        assert(learnerOptional.get().getLearnerName().equals("test"));
    }

}
