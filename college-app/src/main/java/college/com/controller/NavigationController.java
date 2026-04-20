package college.com.controller;

import java.security.Principal;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import college.com.model.Subject;
import college.com.model.Marks;
import college.com.model.User;
import college.com.model.LeaderboardDTO;

import college.com.repository.SubjectRepository;
import college.com.repository.MarksRepository;
import college.com.repository.UserRepository;

@Controller
public class NavigationController {

    private final SubjectRepository subjectRepository;
    private final MarksRepository marksRepository;
    private final UserRepository userRepository; // 🔥 ADDED

    // 🔥 UPDATED CONSTRUCTOR
    public NavigationController(SubjectRepository subjectRepository,
                                MarksRepository marksRepository,
                                UserRepository userRepository) {
        this.subjectRepository = subjectRepository;
        this.marksRepository = marksRepository;
        this.userRepository = userRepository;
    }

    // ================= TRACKER =================
    @GetMapping("/tracker")
    public String tracker(Model model, Principal principal) {

        String username = principal.getName();

        List<Subject> subjects = subjectRepository.findByUserUsername(username);

        int totalClasses = 0;
        int attendedClasses = 0;

        for (Subject s : subjects) {
            totalClasses += s.getTotalClasses();
            attendedClasses += s.getAttendedClasses();
        }

        double attendancePercent = (totalClasses > 0)
                ? (attendedClasses * 100.0) / totalClasses : 0;

        List<Marks> marksList = marksRepository.findByUserUsername(username);

        int totalMarks = 0;
        int obtainedMarks = 0;

        for (Marks m : marksList) {
            totalMarks += m.getTotalMarks();
            obtainedMarks += m.getMarksObtained();
        }

        double marksPercent = (totalMarks > 0)
                ? (obtainedMarks * 100.0) / totalMarks : 0;

        double trackerScore = (attendancePercent * 0.6) + (marksPercent * 0.4);

        model.addAttribute("username", username);
        model.addAttribute("attendancePercent", attendancePercent);
        model.addAttribute("marksPercent", marksPercent);
        model.addAttribute("trackerScore", trackerScore);

        return "tracker";
    }

    // ================= MARKS =================
    // ================= LEADERBOARD =================
    @GetMapping("/leaderboard")
    public String leaderboard(Model model) {

    	List<User> users = userRepository.findByRole("USER");
        List<LeaderboardDTO> leaderboard = new ArrayList<>();

        for (User user : users) {

            String username = user.getUsername();

            List<Subject> subjects = subjectRepository.findByUserUsername(username);

            int totalClasses = 0;
            int attendedClasses = 0;

            for (Subject s : subjects) {
                totalClasses += s.getTotalClasses();
                attendedClasses += s.getAttendedClasses();
            }

            double attendancePercent = (totalClasses > 0)
                    ? (attendedClasses * 100.0) / totalClasses : 0;

            List<Marks> marksList = marksRepository.findByUserUsername(username);

            int totalMarks = 0;
            int obtainedMarks = 0;

            for (Marks m : marksList) {
                totalMarks += m.getTotalMarks();
                obtainedMarks += m.getMarksObtained();
            }

            double marksPercent = (totalMarks > 0)
                    ? (obtainedMarks * 100.0) / totalMarks : 0;

            double score = (attendancePercent * 0.6) + (marksPercent * 0.4);

            leaderboard.add(new LeaderboardDTO(username, score));
        }

        // 🔥 SORT DESCENDING
        leaderboard.sort(Comparator.comparingDouble(LeaderboardDTO::getScore).reversed());

        model.addAttribute("leaderboard", leaderboard);

        return "leaderboard";
    }
}