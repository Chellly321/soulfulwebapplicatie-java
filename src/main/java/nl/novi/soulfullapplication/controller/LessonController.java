package nl.novi.soulfullapplication.controller;

import nl.novi.soulfullapplication.dto.LessonDto;
import nl.novi.soulfullapplication.model.Course;
import nl.novi.soulfullapplication.model.Lesson;
import nl.novi.soulfullapplication.service.CourseService;
import nl.novi.soulfullapplication.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LessonController {
    @Autowired
    LessonService lessonService;

    @PostMapping("/lesson")
    public ResponseEntity<Lesson> addLesson(@RequestBody LessonDto lessonDto){
        Lesson result = lessonService.addLesson(lessonDto);
        return ResponseEntity.ok(result);
    }

}
