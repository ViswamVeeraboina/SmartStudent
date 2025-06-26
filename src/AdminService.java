import java.util.List;
import java.util.Scanner;

public class AdminService {
    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "admin123";
    private StudentDAO studentDAO;
    private Scanner sc;

    public AdminService() {
        studentDAO = new StudentDAO();
        sc = new Scanner(System.in);
    }

    // Admin Login
    public boolean login() {
        System.out.print("👤 Enter username: ");
        String username = sc.nextLine();
        System.out.print("🔒 Enter password: ");
        String password = sc.nextLine();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("✅ Login successful!\n");
            return true;
        } else {
            System.out.println("❌ Invalid credentials.\n");
            return false;
        }
    }

    // Main Menu
    public void showMenu() {
        int choice;
        do {
            System.out.println("\n===== Student Management Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search by Roll No");
            System.out.println("6. Search by Department");
            System.out.println("7. Search by Marks >=");
            System.out.println("8. Exit");
            System.out.print("👉 Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewAllStudents(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: searchByRollNo(); break;
                case 6: searchByDepartment(); break;
                case 7: searchByMarks(); break;
                case 8: System.out.println("👋 Exiting..."); break;
                default: System.out.println("⚠️ Invalid choice. Try again.");
            }

        } while (choice != 8);
    }

    private void addStudent() {
        System.out.println("\n📝 Enter student details:");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Roll No: ");
        String rollNo = sc.nextLine();
        System.out.print("Department: ");
        String dept = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Marks: ");
        int marks = sc.nextInt();
        sc.nextLine();

        Student student = new Student(name, rollNo, dept, email, phone, marks);
        if (studentDAO.addStudent(student)) {
            System.out.println("✅ Student added successfully.");
        } else {
            System.out.println("❌ Failed to add student.");
        }
    }

    private void viewAllStudents() {
        System.out.println("\n📋 All Students:");
        List<Student> students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    private void updateStudent() {
        System.out.print("🔍 Enter ID of student to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter new details:");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Roll No: ");
        String rollNo = sc.nextLine();
        System.out.print("Department: ");
        String dept = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Marks: ");
        int marks = sc.nextInt();
        sc.nextLine();

        Student updated = new Student(id, name, rollNo, dept, email, phone, marks);
        if (studentDAO.updateStudent(updated)) {
            System.out.println("✅ Student updated successfully.");
        } else {
            System.out.println("❌ Failed to update student.");
        }
    }

    private void deleteStudent() {
        System.out.print("🗑️ Enter ID of student to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (studentDAO.deleteStudent(id)) {
            System.out.println("✅ Student deleted.");
        } else {
            System.out.println("❌ Failed to delete student.");
        }
    }

    private void searchByRollNo() {
        System.out.print("🔍 Enter Roll No to search: ");
        String rollNo = sc.nextLine();
        Student s = studentDAO.getStudentByRollNo(rollNo);
        if (s != null) {
            System.out.println("Student Found:\n" + s);
        } else {
            System.out.println("No student found.");
        }
    }

    private void searchByDepartment() {
        System.out.print("🔍 Enter Department to search: ");
        String dept = sc.nextLine();
        List<Student> list = studentDAO.getStudentsByDepartment(dept);
        if (!list.isEmpty()) {
            list.forEach(System.out::println);
        } else {
            System.out.println("No students found in that department.");
        }
    }

    private void searchByMarks() {
        System.out.print("🔍 Enter minimum marks: ");
        int marks = sc.nextInt();
        sc.nextLine();
        List<Student> list = studentDAO.getStudentsByMarksRange(marks);
        if (!list.isEmpty()) {
            list.forEach(System.out::println);
        } else {
            System.out.println("No students with marks above " + marks);
        }
    }
}