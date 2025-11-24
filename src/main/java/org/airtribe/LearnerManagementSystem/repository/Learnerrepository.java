package org.airtribe.LearnerManagementSystem.repository;

import org.airtribe.LearnerManagementSystem.entity.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Learnerrepository extends JpaRepository<Learner, Long> {
    Learner findByLearnerName(String learnerName);


    @Query("SELECT l from Learner l WHERE l.learnerName = ?1")
    Learner findByLearner(String learnerName);
}
