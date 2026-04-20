package college.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import college.com.model.User;
import college.com.model.Attendance;
import college.com.model.Subject;

import college.com.repository.UserRepository;
import college.com.repository.AttendanceRepository;
import college.com.repository.SubjectRepository;

@Controller
public class AdminController {

    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;
    private final SubjectRepository subjectRepository; // 🔥 ADD THIS

    public AdminController(UserRepository userRepository,
                           AttendanceRepository attendanceRepository,
                           SubjectRepository subjectRepository) { // 🔥 ADD HERE

        this.userRepository = userRepository;
        this.attendanceRepository = attendanceRepository;
        this.subjectRepository = subjectRepository; // 🔥 AND HERE
    }

    // ================= ADMIN DASHBOARD =================
    @GetMapping("/admin")
    public String adminDashboard(Model model) {

        System.out.println("🔥 ADMIN PAGE HIT");

        List<User> users = userRepository.findByRole("USER");
        System.out.println("Users: " + users.size());

        List<Attendance> attendance = attendanceRepository.findAll();
        System.out.println("Attendance: " + attendance.size());

        model.addAttribute("users", users);
        model.addAttribute("attendance", attendance);

        return "admin";
    }

    // ================= DELETE USER =================
    @PostMapping("/admin/delete-user")
    public String deleteUser(@RequestParam Long userId, Principal principal) {

        System.out.println("🔥 DELETE REQUEST: " + userId);

        if (principal == null) return "redirect:/login";

        User currentUser = userRepository.findByUsername(principal.getName());
        if (currentUser == null) return "redirect:/login";

        if (!currentUser.getRole().equals("ADMIN")) return "redirect:/tracker";

        if (currentUser.getId().equals(userId)) return "redirect:/admin";

        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {

            // 🔥 STEP 1: Get subjects
            List<Subject> subjects = subjectRepository.findByUser(user);

            for (Subject s : subjects) {

                // 🔥 STEP 2: delete attendance manually
                attendanceRepository.deleteAll(s.getAttendanceList());
            }

            // 🔥 STEP 3: delete subjects
            subjectRepository.deleteAll(subjects);

            // 🔥 STEP 4: delete user
            userRepository.delete(user);

            System.out.println("✅ DELETE SUCCESS");
        }

        return "redirect:/admin";
    }
}