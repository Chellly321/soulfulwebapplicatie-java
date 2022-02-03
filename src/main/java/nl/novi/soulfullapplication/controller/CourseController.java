package nl.novi.soulfullapplication.controller;

import nl.novi.soulfullapplication.model.Course;
import nl.novi.soulfullapplication.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        Course result = courseService.addCourse(course);
        return ResponseEntity.ok(result);
    }

    @GetMapping("{course}")
    public ResponseEntity<Object> getCourse(@PathVariable("course") long id){
        return ResponseEntity.ok().body(courseService.getCourse(id));
    }

}
