package org.airtribe.LearnerManagementSystem.dto;

import java.util.List;

public class LearnerDTO {
    private String learnerName;

    private String learnerEmail;

    private long learnerId;

    private List<CohortDTO> cohorts;

    public LearnerDTO(String learnerName, String learnerEmail, long learnerId, List<CohortDTO> cohorts) {
        this.learnerName = learnerName;
        this.learnerEmail = learnerEmail;
        this.learnerId = learnerId;
        this.cohorts = cohorts;
    }
    public LearnerDTO() {

    }

    public String getLearnerName() {
        return learnerName;
    }

    public void setLearnerName(String learnerName) {
        this.learnerName = learnerName;
    }

    public String getLearnerEmail() {
        return learnerEmail;
    }

    public void setLearnerEmail(String learnerEmail) {
        this.learnerEmail = learnerEmail;
    }

    public long getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(long learnerId) {
        this.learnerId = learnerId;
    }

    public List<CohortDTO> getCohorts() {
        return cohorts;
    }

    public void setCohorts(List<CohortDTO> cohorts) {
        this.cohorts = cohorts;
    }
}
