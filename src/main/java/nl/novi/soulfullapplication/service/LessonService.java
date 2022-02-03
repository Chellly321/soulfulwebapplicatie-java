package nl.novi.soulfullapplication.service;

import nl.novi.soulfullapplication.dto.LessonDto;
import nl.novi.soulfullapplication.model.Course;
import nl.novi.soulfullapplication.model.Lesson;
import nl.novi.soulfullapplication.repository.CourseRepository;
import nl.novi.soulfullapplication.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessonService {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    CourseRepository courseRepository;

    public Lesson addLesson(LessonDto lessonDto){
        Optional<Course> course = courseRepository.findById(lessonDto.getCourseId());
        if (course.isEmpty()){
            throw new RuntimeException("Invalid course id");
        }

        Lesson lesson = new Lesson();
        lesson.setName(lessonDto.getName());
        lesson.setDuration(lessonDto.getDuration());
        lesson.setContent(lessonDto.getContent());
        lesson.setVideoLink(lessonDto.getVideoLink());
        lesson.setCourse(course.get());
        return lessonRepository.save(lesson);
    }
}
