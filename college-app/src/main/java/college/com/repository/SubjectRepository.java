package college.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import college.com.model.Subject;
import college.com.model.User;
import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByUserUsername(String username);

    // 🔥 ADD THIS
    List<Subject> findByUser(User user);
}