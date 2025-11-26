package org.airtribe.LearnerManagementSystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Learner {

    private String learnerName;

    private String learnerEmail;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long learnerId;

    @ManyToMany
    List<Cohort> cohorts;

    public Learner(String learnerName, String learnerEmail) {
        this.learnerName = learnerName;
        this.learnerEmail = learnerEmail;
    }
    public Learner(){

    }

    public String getLearnerName() {
        return learnerName;
    }

    public String getLearnerEmail() {
        return learnerEmail;
    }

    public Long getLearnerId() {
        return learnerId;
    }

    public void setLearnerName(String learnerName) {
        this.learnerName = learnerName;
    }

    public void setLearnerEmail(String learnerEmail) {
        this.learnerEmail = learnerEmail;
    }

    public void setLearnerId(Long learnerId) {
        this.learnerId = learnerId;
    }
}
