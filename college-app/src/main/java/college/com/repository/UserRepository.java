package college.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // 🔥 ADD THIS

import college.com.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> findByRole(String role); // 🔥 used to filter USER / ADMIN
}