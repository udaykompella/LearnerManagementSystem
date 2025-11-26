package org.airtribe.LearnerManagementSystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cohort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cohortId;

    private String cohortName;

    private String cohortDescription;

    @ManyToMany
    List<Learner> learners;

    public Cohort(Long cohortId, String cohortName, String cohortDescription, List<Learner> learners) {
        this.cohortId = cohortId;
        this.cohortName = cohortName;
        this.cohortDescription = cohortDescription;
        this.learners = learners;
    }
    public Cohort() {}

    public Long getCohortId() {
        return cohortId;
    }

    public void setCohortId(Long cohortId) {
        this.cohortId = cohortId;
    }

    public String getCohortName() {
        return cohortName;
    }

    public void setCohortName(String cohortName) {
        this.cohortName = cohortName;
    }

    public String getCohortDescription() {
        return cohortDescription;
    }

    public void setCohortDescription(String cohortDescription) {
        this.cohortDescription = cohortDescription;
    }

    public List<Learner> getLearners() {
        return learners;
    }

    public void setLearners(List<Learner> learners) {
        this.learners = learners;
    }
}

