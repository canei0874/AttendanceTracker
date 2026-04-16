package college.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import college.com.model.Attendance;
import college.com.model.Subject;
import college.com.repository.AttendanceRepository;
import college.com.repository.SubjectRepository;

import java.time.LocalDate;

@Controller
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;
    private final SubjectRepository subjectRepository;

    public AttendanceController(AttendanceRepository attendanceRepository,
                                SubjectRepository subjectRepository) {
        this.attendanceRepository = attendanceRepository;
        this.subjectRepository = subjectRepository;
    }

    @PostMapping("/mark")
    public String markAttendance(@RequestParam Long subjectId,
                                @RequestParam String status) {

        Subject subject = subjectRepository.findById(subjectId).orElse(null);

        if (subject == null) return "redirect:/dashboard";

        // prevent duplicate marking same day
        Attendance existing = attendanceRepository
                .findBySubjectIdAndDate(subjectId, LocalDate.now());

        if (existing != null) {
            return "redirect:/dashboard?error=already_marked";
        }

        Attendance attendance = new Attendance();
        attendance.setDate(LocalDate.now());
        attendance.setStatus(status);
        attendance.setSubject(subject);

        attendanceRepository.save(attendance);

        // 🔥 NEW DYNAMIC LOGIC
        subject.setTotalClasses(subject.getTotalClasses() + 1);

        if (status.equals("Present")) {
            subject.setAttendedClasses(subject.getAttendedClasses() + 1);
        }

        subjectRepository.save(subject);

        return "redirect:/dashboard";
    }
}