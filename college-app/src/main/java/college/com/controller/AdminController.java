package college.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import college.com.repository.UserRepository;
import college.com.repository.AttendanceRepository;

@Controller
public class AdminController {

    private final UserRepository userRepository;
    private final AttendanceRepository attendanceRepository;

    public AdminController(UserRepository userRepository,
                           AttendanceRepository attendanceRepository) {
        this.userRepository = userRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model) {

        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("attendance", attendanceRepository.findAll());

        return "admin";
    }
}