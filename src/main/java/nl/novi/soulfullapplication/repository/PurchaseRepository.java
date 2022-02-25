package nl.novi.soulfullapplication.repository;

import nl.novi.soulfullapplication.model.Course;
import nl.novi.soulfullapplication.model.Purchase;
import nl.novi.soulfullapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Optional<Purchase> findByCourseAndUser(Course course, User user);

    List<Purchase> findByUser(User user);

}
