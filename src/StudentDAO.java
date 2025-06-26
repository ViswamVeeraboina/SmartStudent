import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Add a new student
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (name, roll_no, department, email, phone, marks) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getRollNo());
            pstmt.setString(3, student.getDepartment());
            pstmt.setString(4, student.getEmail());
            pstmt.setString(5, student.getPhone());
            pstmt.setInt(6, student.getMarks());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to add student.");
            e.printStackTrace();
        }
        return false;
    }

    // View all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("marks")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to retrieve students.");
            e.printStackTrace();
        }
        return students;
    }

    // Update student info
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name=?, roll_no=?, department=?, email=?, phone=?, marks=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getRollNo());
            pstmt.setString(3, student.getDepartment());
            pstmt.setString(4, student.getEmail());
            pstmt.setString(5, student.getPhone());
            pstmt.setInt(6, student.getMarks());
            pstmt.setInt(7, student.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Failed to update student.");
            e.printStackTrace();
        }
        return false;
    }

    // Delete student
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Failed to delete student.");
            e.printStackTrace();
        }
        return false;
    }

    // Search by Roll No
    public Student getStudentByRollNo(String rollNo) {
        String sql = "SELECT * FROM students WHERE roll_no=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, rollNo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("marks")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch student by roll no.");
            e.printStackTrace();
        }
        return null;
    }

    // Search by Department
    public List<Student> getStudentsByDepartment(String department) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE department=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, department);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("marks")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch by department.");
            e.printStackTrace();
        }
        return students;
    }

    // Search by Marks Range
    public List<Student> getStudentsByMarksRange(int minMarks) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE marks >= ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, minMarks);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_no"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("marks")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch by marks range.");
            e.printStackTrace();
        }
        return students;
    }
}

