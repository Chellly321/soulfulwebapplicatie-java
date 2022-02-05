package nl.novi.soulfullapplication.repository;

import nl.novi.soulfullapplication.model.Course;
import nl.novi.soulfullapplication.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByCourse(Course course);
}
