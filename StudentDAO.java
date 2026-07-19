package com.laya.sms.dao;

import com.laya.sms.model.Student;

import java.util.List;

/**
 * DAO contract for Student CRUD operations.
 * Coding to an interface demonstrates OOP abstraction and keeps the
 * UI layer decoupled from the JDBC implementation details.
 */
public interface StudentDAO {
    boolean addStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(int id);
    Student getStudentById(int id);
    List<Student> getAllStudents();
}
