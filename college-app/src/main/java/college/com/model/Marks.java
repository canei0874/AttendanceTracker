package college.com.model;

import jakarta.persistence.*;

@Entity
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subjectName;
    private int marksObtained;
    private int totalMarks;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // GETTERS & SETTERS

    public Long getId() { return id; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public int getMarksObtained() { return marksObtained; }
    public void setMarksObtained(int marksObtained) { this.marksObtained = marksObtained; }

    public int getTotalMarks() { return totalMarks; }
    public void setTotalMarks(int totalMarks) { this.totalMarks = totalMarks; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}