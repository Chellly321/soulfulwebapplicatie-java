package nl.novi.soulfullapplication.service;

import nl.novi.soulfullapplication.model.Course;
import nl.novi.soulfullapplication.model.Purchase;
import nl.novi.soulfullapplication.model.User;
import nl.novi.soulfullapplication.repository.CourseRepository;
import nl.novi.soulfullapplication.repository.PurchaseRepository;
import nl.novi.soulfullapplication.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
public class CourseServiceTest {

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PurchaseRepository purchaseRepository;
    
    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @Test
    public void testAddCourse(){
        //ARRANGE
        Course course = new Course();
        Mockito.when(courseRepository.save(any())).thenReturn(course);

        //ACT
        Course result = courseRepository.save(course);

        //ASSERT

        Assertions.assertEquals(course, result);
    }

    @Test
    public void testGetCourse() {
        //ARRANGE
        Long id = 1L;
        Course course = new Course();
        Mockito.when(courseRepository.findById(id)).thenReturn(Optional.of(course));

        //ACT
        Optional<Course> result = courseRepository.findById(id);

        //ASSERT
        Assertions.assertEquals(course, result.get());
    }

    @Test
    public void testBuyCourse() {
        long userId = 1L;
        long courseId = 1L;
        Course course = new Course();
        User user = new User();
        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(purchaseRepository.findByCourseAndUser(course, user)).thenReturn(Optional.empty());

        //ACT
            courseService.buyCourse(userId, courseId);
        //ASSERT
        Mockito.verify(purchaseRepository).save(any());
    }

    @Test
    public void testBuyCourseWhenUserHasAlreadyBoughtTheCourse() {
        long userId = 1L;
        long courseId = 1L;
        Course course = new Course();
        User user = new User();
        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(LocalDate.now());
        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(purchaseRepository.findByCourseAndUser(course, user)).thenReturn(Optional.of(purchase));

        //ACT
        try {
            courseService.buyCourse(userId, courseId);
        }catch (Exception e){
            //ASSERT
            Assertions.assertEquals("U already bought this course.", e.getMessage());
        }
    }

    @Test
    public void testBuyCourseWhenCourseIdIsWrong() {
        long userId = 1L;
        long courseId = 1L;
        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        //ACT
        try {
            courseService.buyCourse(userId, courseId);
        }catch (Exception e){
            //ASSERT
            Assertions.assertEquals("CourseId is wrong.", e.getMessage());
        }
    }

    @Test
    public void testBuyCourseWhenUserIdIsWrong() {
        long userId = 1L;
        long courseId = 1L;
        Course course = new Course();
        User user = new User();
        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        //ACT
        try {
            courseService.buyCourse(userId, courseId);
        }catch (Exception e){
            //ASSERT
            Assertions.assertEquals("UserId not found.", e.getMessage());
        }
    }

    @Test
    public void testShowMyCourses() {
        long userId = 1L;
        User user = new User();
        user.setUsername("Michelle");
        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(LocalDate.now());
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(purchaseRepository.findByUser(user)).thenReturn(List.of(purchase));

        List<Course> result = courseService.showMyCourses(userId);

        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void testShowMyCoursesThrowExceptionForInvalidUserId() {
        long userId = 1L;
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        try{
            courseService.showMyCourses(userId);
        } catch (Exception e) {
            Assertions.assertEquals("UserId not found.", e.getMessage());
        }
    }

    @Test
    public void testDeleteCourse() {
        long id = 1L;
        Mockito.doNothing().when(courseRepository).deleteById(1L);
        //ACT
        courseService.deleteCourse(id);
        //ASSERT
        Mockito.verify(courseRepository).deleteById(1L);
    }

}
