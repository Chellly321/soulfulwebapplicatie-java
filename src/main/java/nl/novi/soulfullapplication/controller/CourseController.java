package nl.novi.soulfullapplication.controller;

import nl.novi.soulfullapplication.model.Course;
import nl.novi.soulfullapplication.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course result = courseService.addCourse(course);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Object> getCourse(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(courseService.getCourse(id));
    }

    @PostMapping("/course/buy")
    public ResponseEntity<String> buyCourse(@RequestParam("userid") Long userId, @RequestParam("courseid") Long courseId) {
        courseService.buyCourse(userId, courseId);
        return ResponseEntity.ok("You bought the course successfully");
    }
    @GetMapping("/mycourses")
    public ResponseEntity<List<Course>> showMyCourses(@RequestParam("userid") Long userId) {
        List<Course> courses = courseService.showMyCourses(userId);
        return ResponseEntity.ok(courses);
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
