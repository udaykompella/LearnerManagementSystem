package org.airtribe.LearnerManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

//Indexes are faster for read at the expense of slowing down writes
//Learners are not composed of cohorts. Cohorts are composed of learners
//so here defining List of cohorts is part of back referencing. Learners consist back reference to the cohorts
// Back referencing is done to avoid duplication of 2 join tables which are created in database
//It is not necessary to add mappedBy but in our usecase we want given a learner we need to know which all cohorts learner is enrolled in.

@Entity
public class Learner {

    @NotNull
    private String learnerName;

    @NotNull
    @Email
    private String learnerEmail;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long learnerId;

    @ManyToMany(mappedBy = "learners")
    @JsonIgnore
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
    public List<Cohort> getCohorts() {
        return cohorts;
    }

    public void setCohorts(List<Cohort> cohorts) {
        this.cohorts = cohorts;
    }
}
