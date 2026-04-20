package college.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class collegeappapplication {

    public static void main(String[] args) {
        SpringApplication.run(collegeappapplication.class, args);
    }
    @Bean
    CommandLineRunner run() {
        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            System.out.println(encoder.encode("admin123"));
        };
    }
}