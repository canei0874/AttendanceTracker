# College App

A comprehensive college management application built with Spring Boot that helps manage student attendance, marks, and overall academic performance.

## 🎯 Features

- **User Authentication & Authorization**: Secure login system with role-based access control (Admin/Student)
- **Attendance Tracking**: Record and monitor student attendance with real-time updates
- **Marks Management**: Manage and track student marks across different subjects
- **Dashboard**: Interactive dashboard with key metrics and statistics
- **Leaderboard**: View student rankings based on performance
- **Subject Management**: Manage subjects and course information
- **Admin Panel**: Administrative controls for managing users, attendance, and marks
- **Responsive UI**: Clean and intuitive user interface built with Thymeleaf templates

## 🛠️ Technologies Used

- **Backend Framework**: Spring Boot 3.2.5
- **Language**: Java 17
- **Database**: MySQL
- **Authentication**: Spring Security with BCrypt password encoding
- **Template Engine**: Thymeleaf
- **ORM**: Spring Data JPA with Hibernate
- **Build Tool**: Maven

## 📋 Prerequisites

Before running the application, ensure you have the following installed:

- Java Development Kit (JDK) 17 or higher
- MySQL Server 5.7 or higher
- Maven 3.6 or higher
- Git (for version control)

## 🚀 Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd college-app
```

### 2. Database Setup

Create a MySQL database:
```sql
CREATE DATABASE college_db;
```

The application will automatically create the necessary tables on startup (using Hibernate DDL auto-update).

### 3. Configure Database Connection

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/college_db
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

Update the `username` and `password` to match your MySQL credentials.

### 4. Build the Project

```bash
mvn clean install
```

### 5. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080` by default.

## 📁 Project Structure

```
college-app/
├── src/
│   ├── main/
│   │   ├── java/college/com/
│   │   │   ├── collegeappapplication.java       # Main Spring Boot application
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java           # Spring Security configuration
│   │   │   ├── controller/
│   │   │   │   ├── AdminController.java          # Admin operations
│   │   │   │   ├── AttendanceController.java     # Attendance management
│   │   │   │   ├── DashboardController.java      # Dashboard view
│   │   │   │   ├── LoginController.java          # Authentication
│   │   │   │   ├── MarksController.java          # Marks management
│   │   │   │   ├── NavigationController.java     # Navigation handling
│   │   │   │   ├── SubjectController.java        # Subject management
│   │   │   │   └── UserController.java           # User management
│   │   │   ├── model/
│   │   │   │   ├── User.java                     # User entity
│   │   │   │   ├── Attendance.java               # Attendance entity
│   │   │   │   ├── Marks.java                    # Marks entity
│   │   │   │   ├── Subject.java                  # Subject entity
│   │   │   │   └── LeaderboardDTO.java           # Leaderboard data transfer object
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java           # User data access
│   │   │   │   ├── AttendanceRepository.java     # Attendance data access
│   │   │   │   ├── MarksRepository.java          # Marks data access
│   │   │   │   └── SubjectRepository.java        # Subject data access
│   │   │   └── service/
│   │   │       └── CustomUserDetailsService.java # Custom user authentication
│   │   └── resources/
│   │       ├── application.properties            # Application configuration
│   │       └── templates/
│   │           ├── login.html                    # Login page
│   │           ├── register.html                 # Registration page
│   │           ├── dashboard.html                # Student dashboard
│   │           ├── admin.html                    # Admin panel
│   │           ├── attendance.html               # Attendance tracker
│   │           ├── marks.html                    # Marks view
│   │           ├── leaderboard.html              # Leaderboard view
│   │           └── fragments/
│   │               └── navbar.html               # Navigation bar component
│   └── test/                                     # Test files
└── pom.xml                                       # Maven configuration
```

## 💻 Usage

### Login
- Access the application at `http://localhost:8080`
- Use the login page to authenticate with your credentials
- New users can register from the registration page

### Admin Features
- Access admin panel to manage users
- Record and update attendance
- Manage student marks
- View and manage subjects

### Student Features
- View personal dashboard with key statistics
- Check attendance records
- View marks and performance
- Check leaderboard rankings

## 🔐 Security

- Passwords are encrypted using BCrypt
- Spring Security is configured to protect sensitive endpoints
- Role-based access control (RBAC) for admin and student users
- CSRF protection enabled by default

## 📝 Notes

- The application uses Hibernate's JPA implementation for ORM
- Database schema is automatically created/updated on startup
- SQL queries are logged in the console for debugging

## 🤝 Contributing

Feel free to submit issues or pull requests for improvements and bug fixes.

## 📄 License

This project is open source and available for educational purposes.

## 📞 Support

For issues or questions, please open an issue in the repository.

---

**Version**: 1.0  
**Last Updated**: April 2026
