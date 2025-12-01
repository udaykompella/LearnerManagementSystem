package org.airtribe.LearnerManagementSystem.repository;

import org.airtribe.LearnerManagementSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
