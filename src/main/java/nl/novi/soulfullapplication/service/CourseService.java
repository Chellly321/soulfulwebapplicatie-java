package nl.novi.soulfullapplication.service;

import nl.novi.soulfullapplication.model.Course;
import nl.novi.soulfullapplication.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public Course addCourse(Course course){
        return courseRepository.save(course);
    }
    public Optional<Course> getCourse(long id){
        return courseRepository.findById(id);
    }
}
