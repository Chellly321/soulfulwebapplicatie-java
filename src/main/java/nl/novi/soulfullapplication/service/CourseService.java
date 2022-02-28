package nl.novi.soulfullapplication.service;

import nl.novi.soulfullapplication.model.Course;
import nl.novi.soulfullapplication.model.Purchase;
import nl.novi.soulfullapplication.model.User;
import nl.novi.soulfullapplication.repository.CourseRepository;
import nl.novi.soulfullapplication.repository.PurchaseRepository;
import nl.novi.soulfullapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> getCourse(long id) {
        return courseRepository.findById(id);
    }

    public void buyCourse(long userId, long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new RuntimeException("CourseId is wrong.");
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("UserId not found.");
        }
        Optional<Purchase> purchase = purchaseRepository.findByCourseAndUser(course.get(), user.get());

        if (purchase.isEmpty()) { // didnt buy it earlier
            saveToDatabase(course, user);
        } else {
            LocalDate expiration = purchase.get().getPurchaseDate().plusMonths(6);
            if (LocalDate.now().isAfter(expiration)) { // buy it again because the earlier course has expired
                saveToDatabase(course, user);
            } else {
                throw new RuntimeException("U already bought this course.");
            }
        }

    }

    private void saveToDatabase(Optional<Course> course, Optional<User> user) {
        Purchase purchaseObject = new Purchase();
        purchaseObject.setCourse(course.get());
        purchaseObject.setUser(user.get());
        purchaseObject.setPurchaseDate(LocalDate.now());
        purchaseRepository.save(purchaseObject);
    }

    public List<Course> showMyCourses(long userId) {
        List<Course> myCourse = new ArrayList<>();
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("UserId not found.");
        }
        List<Purchase> purchasesList = purchaseRepository.findByUser(user.get());
        for (Purchase purchase : purchasesList) {
            LocalDate expiration = purchase.getPurchaseDate().plusMonths(6);
            if (!LocalDate.now().isAfter(expiration)) {
                myCourse.add(purchase.getCourse());
            }
        }
        return myCourse;
    }

    public void deleteCourse(long id) {
        courseRepository.deleteById(id);
    }


}
