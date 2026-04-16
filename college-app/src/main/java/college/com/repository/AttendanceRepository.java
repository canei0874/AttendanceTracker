package college.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import college.com.model.Attendance;
import java.time.LocalDate;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Attendance findBySubjectIdAndDate(Long subjectId, LocalDate date);
}