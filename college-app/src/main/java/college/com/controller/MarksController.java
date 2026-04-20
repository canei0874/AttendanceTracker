package college.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import college.com.model.Marks;
import college.com.model.User;
import college.com.repository.MarksRepository;
import college.com.repository.UserRepository;

import java.security.Principal;
import java.util.List;

@Controller
public class MarksController {

    private final MarksRepository marksRepository;
    private final UserRepository userRepository;

    public MarksController(MarksRepository marksRepository, UserRepository userRepository) {
        this.marksRepository = marksRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/marks")
    public String marksPage(Model model, Principal principal) {

        String username = principal.getName();

        List<Marks> marksList = marksRepository.findByUserUsername(username);
        model.addAttribute("marksList", marksList);

        return "marks";
    }

    @PostMapping("/add-marks")
    public String addMarks(@RequestParam String subjectName,
                           @RequestParam int marksObtained,
                           @RequestParam int totalMarks,
                           Principal principal) {

        User user = userRepository.findByUsername(principal.getName());

        Marks marks = new Marks();
        marks.setSubjectName(subjectName);
        marks.setMarksObtained(marksObtained);
        marks.setTotalMarks(totalMarks);
        marks.setUser(user);

        marksRepository.save(marks);

        return "redirect:/marks";
    }
}