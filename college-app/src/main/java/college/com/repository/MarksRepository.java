package college.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import college.com.model.Marks;

import java.util.List;

public interface MarksRepository extends JpaRepository<Marks, Long> {

    List<Marks> findByUserUsername(String username);
}