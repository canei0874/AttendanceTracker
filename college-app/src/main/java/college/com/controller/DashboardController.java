package college.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import college.com.model.Subject;
import college.com.repository.SubjectRepository;

import java.security.Principal;
import java.util.List;

@Controller
public class DashboardController {

    private final SubjectRepository subjectRepository;

    public DashboardController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {

        // 🔥 Get logged-in username
        String username = principal.getName();
        model.addAttribute("username", username);

        // 🔥 Fetch subjects for this user
        List<Subject> subjects = subjectRepository.findByUserUsername(username);
        model.addAttribute("subjects", subjects);

        return "dashboard";
    }
}