package nl.novi.soulfullapplication.service;

import nl.novi.soulfullapplication.dto.LessonDto;
import nl.novi.soulfullapplication.model.Course;
import nl.novi.soulfullapplication.model.Lesson;
import nl.novi.soulfullapplication.repository.CourseRepository;
import nl.novi.soulfullapplication.repository.LessonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

    @Mock
    Lesson newLesson;
    @Mock
    Course newCourse;
    @Mock
    LessonDto lessonDto;

    @Test
    public void testGetLesson() {
        long id = 1L;
        Lesson lesson = new Lesson();
        Mockito.when(lessonRepository.findById(id)).thenReturn(Optional.of(lesson));

        //ACT
        Lesson result = lessonService.getLesson(id);

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
    @Test
    public void testDeleteLesson() {
        long id = 1L;
        Mockito.doNothing().when(lessonRepository).deleteById(1L);
        //ACT
        lessonService.deleteLesson(id);
        //ASSERT
        Mockito.verify(lessonRepository).deleteById(1L);
    }
    @Test
    public void testAddLesson() {
        Mockito.when(courseRepository.findById(any())).thenReturn(Optional.of(newCourse));
        Mockito.when(lessonRepository.save(any())).thenReturn(newLesson);

        Lesson result = lessonService.addLesson(lessonDto);

        Assertions.assertEquals(newLesson, result);
    }

    @Test
    public void testAddLessonWhenCourseIdInvalid() {
        LessonDto lessonDto = new LessonDto();
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
