import java.util.Scanner;

interface StudentOperations {
    void enrollCourse();
    void dropCourse();
    void calculateGrades();
}

interface FacultyOperations {
    void assignCourse();
    void unassignCourse();
}

interface UniversityMemberOperations extends StudentOperations, FacultyOperations {
    void viewProfile();
    void updateProfile();
}

class Student implements UniversityMemberOperations {
    public String name;
    public String department;
    public int studentId;
    private double marks;

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public void enrollCourse() {
        System.out.println(name + " enrolled in a course.");
    }

    public void dropCourse() {
        System.out.println(name + " dropped a course.");
    }

    public void calculateGrades() {
        if (marks >= 90) {
            System.out.println("Grade: A");
        } else if (marks >= 80) {
            System.out.println("Grade: B");
        } else if (marks >= 70) {
            System.out.println("Grade: C");
        } else if (marks >= 60) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: F");
        }
    }

    public void viewProfile() {
        System.out.println("Viewing profile of " + name);
    }

    public void updateProfile() {
        System.out.println("Updating profile of " + name);
    }

    public void assignCourse() {}

    public void unassignCourse() {}
}

class Faculty implements UniversityMemberOperations {
    public String name;
    public String department;
    public String facultyId;

    public void assignCourse() {
        System.out.println(name + " assigned to a course.");
    }

    public void unassignCourse() {
        System.out.println(name + " unassigned from a course.");
    }

    public void enrollCourse() {}

    public void dropCourse() {}

    public void calculateGrades() {}

    public void viewProfile() {
        System.out.println("Viewing profile of " + name);
    }

    public void updateProfile() {
        System.out.println("Updating profile of " + name);
    }
}

public class UniversityDatabase {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();
        Faculty faculty = new Faculty();

        System.out.print("Enter student name: ");
        student.name = scanner.nextLine();
        System.out.print("Enter student department: ");
        student.department = scanner.nextLine();
        System.out.print("Enter student ID: ");
        student.studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter faculty name: ");
        faculty.name = scanner.nextLine();
        System.out.print("Enter faculty department: ");
        faculty.department = scanner.nextLine();
        System.out.print("Enter faculty ID: ");
        faculty.facultyId = scanner.nextLine();
        System.out.print("Enter marks for student: ");
        double marks = scanner.nextDouble();
        scanner.nextLine();
        student.setMarks(marks);

        student.enrollCourse();
        student.dropCourse();
        student.calculateGrades();
        student.viewProfile();
        student.updateProfile();

        faculty.assignCourse();
        faculty.unassignCourse();
        faculty.viewProfile();
        faculty.updateProfile();
    }
}