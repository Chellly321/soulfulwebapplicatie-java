package nl.novi.soulfullapplication.service;

import nl.novi.soulfullapplication.model.Course;
import nl.novi.soulfullapplication.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public Course addCourse(Course course){
        return courseRepository.save(course);
    }
}
