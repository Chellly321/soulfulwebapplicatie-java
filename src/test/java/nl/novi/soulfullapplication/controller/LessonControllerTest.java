package nl.novi.soulfullapplication.controller;

import nl.novi.soulfullapplication.dto.LessonDto;
import nl.novi.soulfullapplication.model.Lesson;
import nl.novi.soulfullapplication.service.LessonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
public class LessonControllerTest {

    @MockBean
    LessonService lessonService;

    @Autowired
    LessonController lessonController;

    @Test
    public void testAddLesson() {
        //ARRANGE
        LessonDto lessonDto = new LessonDto();
        Lesson lesson = new Lesson();
        Mockito.when(lessonService.addLesson(lessonDto)).thenReturn(lesson);

        //ACT
        ResponseEntity<Lesson> result = lessonController.addLesson(lessonDto);

        //ASSERT
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(lesson, result.getBody());
    }
    @Test
    public void testGetLesson() {
        //ARRANGE
        Long id = 1L;
        Lesson lesson = new Lesson();
        Mockito.when(lessonService.getLesson(id)).thenReturn(lesson);

        //ACT
        ResponseEntity<Lesson> result = lessonController.getLesson(id);

        //ASSERT
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(lesson, result.getBody());
    }

    @Test
    public void testGetLessons(){
        long id = 1L;
        Lesson lessons = new Lesson();
        Mockito.when(lessonService.getLessons(id)).thenReturn(List.of(lessons));

        ResponseEntity<List<Lesson>> result = lessonController.getLessons(id);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(List.of(lessons), result.getBody());

    }
    @Test
    public void testDeleteLesson() {

        Mockito.doNothing().when(lessonService).deleteLesson(anyLong());
        ResponseEntity<String> result = lessonController.deleteLesson(1L);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals("Lesson has been deleted!", result.getBody());

    }

}
