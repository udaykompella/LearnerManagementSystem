package org.airtribe.LearnerManagementSystem.service;

import org.airtribe.LearnerManagementSystem.entity.Cohort;
import org.airtribe.LearnerManagementSystem.entity.Course;
import org.airtribe.LearnerManagementSystem.repository.CohortRepository;
import org.airtribe.LearnerManagementSystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseManagementService{

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CohortRepository cohortRepository;

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> fetchAllCourses() {
        return courseRepository.findAll();
    }

    public Course assignCohortsToCourses(Long cohortId, Long courseId) throws CourseNotFoundException, CohortNotFoundException {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if(!courseOptional.isPresent()) {
            throw new CourseNotFoundException("Course not found with id:" + courseId);
        }
        Optional<Cohort> cohortOptional = cohortRepository.findById(cohortId);
        if(!cohortOptional.isPresent()) {
            throw new CohortNotFoundException("Cohort not found with id:" + cohortId);
        }
        Course course = courseOptional.get();

        List<Cohort> existingCohorts = course.getCohorts();
        existingCohorts.add(cohortOptional.get());
        return courseRepository.save(course);
    }
}
