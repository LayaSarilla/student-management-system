package com.laya.sms.dao;

import com.laya.sms.model.Student;
import com.laya.sms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC-based implementation of StudentDAO backed by MySQL.
 * Uses PreparedStatement throughout to prevent SQL injection.
 */
public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (name, age, course, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCourse());
            ps.setString(4, student.getEmail());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, age = ?, course = ?, email = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCourse());
            ps.setString(4, student.getEmail());
            ps.setInt(5, student.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching student: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                students.add(mapRow(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }
        return students;
    }

    private Student mapRow(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("course"),
                rs.getString("email")
        );
    }
}
