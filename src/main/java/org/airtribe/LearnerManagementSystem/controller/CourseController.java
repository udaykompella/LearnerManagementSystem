package org.airtribe.LearnerManagementSystem.controller;

import org.airtribe.LearnerManagementSystem.entity.Course;
import org.airtribe.LearnerManagementSystem.service.CohortNotFoundException;
import org.airtribe.LearnerManagementSystem.service.CourseManagementService;
import org.airtribe.LearnerManagementSystem.service.CourseNotFoundException;
import org.airtribe.LearnerManagementSystem.service.LearnerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseManagementService courseManagementService;
    @PostMapping("/courses")
    private Course addCourse(@RequestBody Course course) {
        return courseManagementService.createCourse(course);
    }
    @GetMapping("/courses")
    private List<Course> fetchCourses(){
        return  courseManagementService.fetchAllCourses();
    }
    @PostMapping("/assignCohortsToCourses")
    private Course assignCohortsToCourses(@RequestParam("cohortId") Long cohortId, @RequestParam("courseId") Long courseId) throws CohortNotFoundException, CourseNotFoundException {
        return courseManagementService.assignCohortsToCourses(cohortId,courseId);
    }
    @ExceptionHandler(CohortNotFoundException.class)
    public ResponseEntity handleCohortNotFoundException(CohortNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity handleCourseNotFoundException(CourseNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
