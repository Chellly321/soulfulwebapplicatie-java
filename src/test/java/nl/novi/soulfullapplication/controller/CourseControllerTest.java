package nl.novi.soulfullapplication.controller;

import nl.novi.soulfullapplication.model.Course;
import nl.novi.soulfullapplication.service.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
public class CourseControllerTest {
    @MockBean
    CourseService courseService;

    @Autowired
    CourseController courseController;

    @Test
    public void testAddCourse() {
        //ARRANGE
        Course course = new Course();
        Mockito.when(courseService.addCourse(course)).thenReturn(course);

        //ACT
        ResponseEntity<Course> result = courseController.addCourse(course);

        //ASSERT
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(course, result.getBody());
    }

    @Test
    public void testGetCourse() {
        //ARRANGE
        Long id = 1L;
        Course course = new Course();
        Mockito.when(courseService.getCourse(id)).thenReturn(Optional.of(course));

        //ACT
        ResponseEntity<Object> result = courseController.getCourse(id);

        //ASSERT
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(course, ((Optional)result.getBody()).get());
    }
    @Test
    public void testBuyCourse(){
        //ARRANGE
        Long userId = 1L;
        Long courseId = 1L;
        Mockito.doNothing().when(courseService).buyCourse(userId, courseId);

        //ACT
        ResponseEntity<String> result = courseController.buyCourse(userId, courseId);

        //ASSERT
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals("You bought the course successfully", result.getBody());

    }
    @Test
    public void testShowMyCourses(){
        //ARRANGE
        Long userId = 1L;
        Course course = new Course();
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        Mockito.when(courseService.showMyCourses(userId)).thenReturn(courses);

        //ACT
        ResponseEntity<List<Course>> result = courseController.showMyCourses(userId);

        //ASSERT
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(1, result.getBody().size());
    }

    @Test
    public void testDeleteCourse(){
        //ARRANGE
        Mockito.doNothing().when(courseService).deleteCourse(anyLong());

        //ACT
        ResponseEntity<String> result = courseController.deleteCourse(1L);

        //ASSERT
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }




}
