package com.laya.sms.ui;

import com.laya.sms.dao.StudentDAO;
import com.laya.sms.dao.StudentDAOImpl;
import com.laya.sms.model.Student;

import java.util.List;
import java.util.Scanner;

/**
 * Console-driven menu that lets the user add, update, delete, and view
 * student records. This is the presentation layer; all persistence is
 * delegated to StudentDAO.
 */
public class ConsoleMenu {

    private final StudentDAO studentDAO = new StudentDAOImpl();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean running = true;

        System.out.println("=================================================");
        System.out.println("      STUDENT MANAGEMENT SYSTEM");
        System.out.println("=================================================");

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> searchStudent();
                case 0 -> {
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n--------------------- MENU ---------------------");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Search Student by ID");
        System.out.println("0. Exit");
        System.out.println("--------------------------------------------------");
    }

    private void addStudent() {
        System.out.println("\n-- Add New Student --");
        String name = readString("Name: ");
        int age = readInt("Age: ");
        String course = readString("Course: ");
        String email = readString("Email: ");

        Student student = new Student(name, age, course, email);
        boolean success = studentDAO.addStudent(student);

        System.out.println(success ? "Student added successfully!" : "Failed to add student.");
    }

    private void viewAllStudents() {
        System.out.println("\n-- All Students --");
        List<Student> students = studentDAO.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.printf("%-4s %-20s %-5s %-15s %-25s%n", "ID", "Name", "Age", "Course", "Email");
        System.out.println("--------------------------------------------------------------------");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private void updateStudent() {
        System.out.println("\n-- Update Student --");
        int id = readInt("Enter Student ID to update: ");
        Student existing = studentDAO.getStudentById(id);

        if (existing == null) {
            System.out.println("No student found with ID: " + id);
            return;
        }

        System.out.println("Current record: " + existing);
        String name = readString("New Name (" + existing.getName() + "): ");
        int age = readInt("New Age (" + existing.getAge() + "): ");
        String course = readString("New Course (" + existing.getCourse() + "): ");
        String email = readString("New Email (" + existing.getEmail() + "): ");

        existing.setName(name);
        existing.setAge(age);
        existing.setCourse(course);
        existing.setEmail(email);

        boolean success = studentDAO.updateStudent(existing);
        System.out.println(success ? "Student updated successfully!" : "Failed to update student.");
    }

    private void deleteStudent() {
        System.out.println("\n-- Delete Student --");
        int id = readInt("Enter Student ID to delete: ");
        boolean success = studentDAO.deleteStudent(id);
        System.out.println(success ? "Student deleted successfully!" : "No student found with ID: " + id);
    }

    private void searchStudent() {
        System.out.println("\n-- Search Student --");
        int id = readInt("Enter Student ID: ");
        Student student = studentDAO.getStudentById(id);

        if (student == null) {
            System.out.println("No student found with ID: " + id);
        } else {
            System.out.printf("%-4s %-20s %-5s %-15s %-25s%n", "ID", "Name", "Age", "Course", "Email");
            System.out.println(student);
        }
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
