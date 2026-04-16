package college.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;

import college.com.model.User;
import college.com.repository.UserRepository;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Show register page
    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    // ✅ Handle form submit
    @PostMapping("/register")
    public String register(User user, Model model) {

        // 🔥 Check duplicate username
        User existing = userRepository.findByUsername(user.getUsername());

        if (existing != null) {
            model.addAttribute("error", "Username already exists!");
            return "register";
        }

        // 🔐 Encrypt password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        user.setRole("USER");
        userRepository.save(user);

        return "redirect:/login";
    }
}