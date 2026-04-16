package college.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import college.com.model.Subject;
import college.com.model.User;
import college.com.repository.SubjectRepository;
import college.com.repository.UserRepository;

import java.security.Principal;

@Controller
public class SubjectController {

    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public SubjectController(SubjectRepository subjectRepository,
                             UserRepository userRepository) {
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/add-subject")
    public String addSubject(@RequestParam String name,
                            @RequestParam int totalClasses,
                            @RequestParam int attendedClasses,
                            Principal principal) {

        User user = userRepository.findByUsername(principal.getName());

        // 🔥 VALIDATION
        if (attendedClasses > totalClasses) {
            return "redirect:/dashboard?error=invalid";
        }

        Subject subject = new Subject();
        subject.setName(name);
        subject.setTotalClasses(totalClasses);
        subject.setAttendedClasses(attendedClasses);
        subject.setUser(user);

        subjectRepository.save(subject);

        return "redirect:/dashboard";
    }
    @PostMapping("/delete-subject")
    public String deleteSubject(@RequestParam Long subjectId, Principal principal) {

        String username = principal.getName();

        Subject subject = subjectRepository.findById(subjectId).orElse(null);

        // 🔒 Safety check (user can delete only their subject)
        if (subject != null && subject.getUser().getUsername().equals(username)) {
            subjectRepository.delete(subject);
        }

        return "redirect:/dashboard";
    }
}