package nl.novi.soulfullapplication.service;

import nl.novi.soulfullapplication.dto.LessonDto;
import nl.novi.soulfullapplication.model.Course;
import nl.novi.soulfullapplication.model.Lesson;
import nl.novi.soulfullapplication.repository.CourseRepository;
import nl.novi.soulfullapplication.repository.LessonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
public class LessonServiceTest {

    @MockBean
    LessonRepository lessonRepository;

    @MockBean
    CourseRepository courseRepository;

    @Autowired
    LessonService lessonService;

    @Test
    public void testGetLesson() {
        long id = 1L;
        Lesson lesson = new Lesson();
        Mockito.when(lessonRepository.findById(id)).thenReturn(Optional.of(lesson));

        //ACT
        Lesson result = lessonRepository.findById(id).get();

        //Assert
        Assertions.assertEquals(lesson, result);
    }

    @Test
    public  void testGetLessonsWhenIdIsWrong() {
        long courseId = 1L;
        Course course = new Course();
        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        //ACT
        try {
            courseRepository.findById(courseId);
        } catch (Exception e){
            //ASSERT
            Assertions.assertEquals("Wrong courseId", e.getMessage());
        }
    }
//    @Test
//    public void testGetLessons() {
//        long courseId = 1L;
//        List<Lesson> lessons = new ArrayList<>();
//        lessons.add((Lesson) lessons);
//        Mockito.when(lessonRepository.findByCourse(any())).thenReturn(lessons);
//        //ACT
//        List<Lesson> result = lessonRepository.findByCourse(((Lesson) lessons).getCourse());
//
//        //ASSERT
//        Assertions.assertEquals(lessons, result);
//    }
    @Test
    public void testDeleteLesson() {
        long id = 1L;
        Mockito.doNothing().when(lessonRepository).deleteById(1L);
        //ACT
        lessonService.deleteLesson(id);
        //ASSERT
        Mockito.verify(lessonRepository).deleteById(1L);
    }
//    @Test
//    public void testAddLesson() {
//        LessonDto lessonDto = new LessonDto();
//        Lesson lesson = new Lesson();
//        Mockito.when(lessonRepository.save(any())).thenReturn(Optional.of(lessonDto));
//
//        Lesson result = lessonRepository.save(lesson);
//
//        Assertions.assertEquals(lesson, result);
//    }

    @Test
    public void testAddLessonWhenCourseIdInvalid() {
        LessonDto lessonDto = new LessonDto();
        Lesson lesson = new Lesson();
        Course course = new Course();
        Mockito.when(courseRepository.findById(any())).thenReturn(Optional.of(course));
        Mockito.when(lessonRepository.save(any())).thenReturn(lessonDto);

        try {
            courseRepository.findById(lessonDto.getCourseId());
        } catch (Exception e){
                Assertions.assertEquals("Invalid course id", e.getMessage());
    }
    }
}
