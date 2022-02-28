package nl.novi.soulfullapplication.controller;

import nl.novi.soulfullapplication.dto.LessonDto;
import nl.novi.soulfullapplication.model.Lesson;
import nl.novi.soulfullapplication.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LessonController {
    @Autowired
    LessonService lessonService;

    @PostMapping("/lesson")
    public ResponseEntity<Lesson> addLesson(@RequestBody LessonDto lessonDto) {
        Lesson result = lessonService.addLesson(lessonDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/lesson/{id}")
    public ResponseEntity<Lesson> getLesson(@PathVariable("id") long id) {
        Lesson lesson = lessonService.getLesson(id);
        return ResponseEntity.ok(lesson);
    }

    @GetMapping("/lesson/bycourse/{courseId}")
    public ResponseEntity<List<Lesson>> getLessons(@PathVariable("courseId") long id) {
        List<Lesson> lessons = lessonService.getLessons(id);
        return ResponseEntity.ok(lessons);
    }

    @DeleteMapping("/lesson/{id}")
    public ResponseEntity<String> deleteLesson(@PathVariable long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.ok("Lesson has been deleted!");
    }


}
