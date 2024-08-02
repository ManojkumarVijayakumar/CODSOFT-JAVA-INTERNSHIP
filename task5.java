import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Course class
class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private List<String> enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolledStudentsCount() {
        return enrolledStudents.size();
    }

    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }

    public void enrollStudent(String studentID) {
        if (isFull()) {
            throw new IllegalStateException("Course " + courseCode + " is already full.");
        }
        enrolledStudents.add(studentID);
    }

    public void removeStudent(String studentID) {
        enrolledStudents.remove(studentID);
    }

    public boolean isStudentEnrolled(String studentID) {
        return enrolledStudents.contains(studentID);
    }
}

// CourseManager class
class CourseManager {
    private Map<String, Course> courses;

    public CourseManager() {
        this.courses = new HashMap<>();
    }

    public void addCourse(Course course) {
        courses.put(course.getCourseCode(), course);
    }

    public void removeCourse(String courseCode) {
        courses.remove(courseCode);
    }

    public Course getCourse(String courseCode) {
        return courses.get(courseCode);
    }

    public void displayAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            System.out.println("Available Courses:");
            for (Course course : courses.values()) {
                System.out.println("Course Code: " + course.getCourseCode());
                System.out.println("Title: " + course.getTitle());
                System.out.println("Description: " + course.getDescription());
                System.out.println("Capacity: " + course.getCapacity());
                System.out.println("Enrolled Students: " + course.getEnrolledStudentsCount());
                System.out.println("Available Slots: " + (course.getCapacity() - course.getEnrolledStudentsCount()));
                System.out.println("---------------------------------");
            }
        }
    }
}

// Student class
class Student {
    private String studentID;
    private String name;
    private List<String> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    public void dropCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }
}

// StudentManager class
class StudentManager {
    private Map<String, Student> students;

    public StudentManager() {
        this.students = new HashMap<>();
    }

    public void addStudent(Student student) {
        students.put(student.getStudentID(), student);
    }

    public void removeStudent(String studentID) {
        students.remove(studentID);
    }

    public Student getStudent(String studentID) {
        return students.get(studentID);
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered.");
        } else {
            System.out.println("Registered Students:");
            for (Student student : students.values()) {
                System.out.println("Student ID: " + student.getStudentID());
                System.out.println("Name: " + student.getName());
                System.out.println("Registered Courses: " + student.getRegisteredCourses());
                System.out.println("---------------------------------");
            }
        }
    }
}

// Main class
public class task5 {
    public static void main(String[] args) {
        CourseManager courseManager = new CourseManager();
        StudentManager studentManager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        // Adding initial courses
        Course java101 = new Course("CS101", "Introduction to Java", "Learn the basics of Java programming.", 30);
        Course python101 = new Course("CS102", "Introduction to Python", "Learn the basics of Python programming.", 25);

        courseManager.addCourse(java101);
        courseManager.addCourse(python101);

        // Adding initial students
        Student alice = new Student("S001", "Alice Smith");
        Student bob = new Student("S002", "Bob Johnson");

        studentManager.addStudent(alice);
        studentManager.addStudent(bob);

        boolean exit = false;

        while (!exit) {
            System.out.println();
            System.out.println("Welcome to Course Registration System");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Display Registered Students");
            System.out.println("3. Register Student for a Course");
            System.out.println("4. Drop Student from a Course");
            System.out.println("5. Add a New Course");
            System.out.println("6. Remove a Course");
            System.out.println("7. Add a New Student");
            System.out.println("8. Remove a Student");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    courseManager.displayAllCourses();
                    break;
                case 2:
                    studentManager.displayAllStudents();
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter course code to register: ");
                    String courseCode = scanner.nextLine();

                    Student student = studentManager.getStudent(studentID);
                    Course course = courseManager.getCourse(courseCode);

                    if (student != null && course != null && !course.isFull() && !course.isStudentEnrolled(studentID)) {
                        student.registerCourse(courseCode);
                        course.enrollStudent(studentID);
                        System.out.println("Student registered successfully.");
                    } else {
                        System.out.println("Registration failed. Please check student ID, course code, or course capacity.");
                    }
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter course code to drop: ");
                    courseCode = scanner.nextLine();

                    student = studentManager.getStudent(studentID);
                    course = courseManager.getCourse(courseCode);

                    if (student != null && course != null && course.isStudentEnrolled(studentID)) {
                        student.dropCourse(courseCode);
                        course.removeStudent(studentID);
                        System.out.println("Student dropped from course successfully.");
                    } else {
                        System.out.println("Dropping student from course failed. Please check student ID or course code.");
                    }
                    break;
                case 5:
                    System.out.print("Enter course code: ");
                    courseCode = scanner.nextLine();
                    System.out.print("Enter course title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter course description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter course capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    Course newCourse = new Course(courseCode, title, description, capacity);
                    courseManager.addCourse(newCourse);
                    System.out.println("Course added successfully.");
                    break;
                case 6:
                    System.out.print("Enter course code to remove: ");
                    courseCode = scanner.nextLine();
                    courseManager.removeCourse(courseCode);
                    System.out.println("Course removed successfully.");
                    break;
                case 7:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();

                    Student newStudent = new Student(studentID, studentName);
                    studentManager.addStudent(newStudent);
                    System.out.println("Student added successfully.");
                    break;
                case 8:
                    System.out.print("Enter student ID to remove: ");
                    studentID = scanner.nextLine();
                    studentManager.removeStudent(studentID);
                    System.out.println("Student removed successfully.");
                    break;
                case 9:
                    exit = true;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 9.");
                    break;
            }
        }

        scanner.close();
    }
}