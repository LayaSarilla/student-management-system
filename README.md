# Student Management System

A console-based Java application to **add, update, delete, and view** student
records, using **JDBC** to connect to a **MySQL** database. Built with clean
OOP design (encapsulation, DAO pattern, interface abstraction).

## Features

- Add a new student
- View all students
- Update an existing student's details
- Delete a student
- Search a student by ID
- Uses `PreparedStatement` everywhere (SQL-injection safe)

## Tech Stack

- Java 17+
- JDBC
- MySQL 8
- Plain `javac` build (no framework needed) — Maven-friendly structure included

## Project Structure

```
student-management-system/
├── sql/
│   └── schema.sql                 # DB schema (run once)
├── src/main/java/com/laya/sms/
│   ├── Main.java                  # Entry point
│   ├── model/Student.java         # Entity (OOP: encapsulation)
│   ├── dao/StudentDAO.java        # DAO interface (OOP: abstraction)
│   ├── dao/StudentDAOImpl.java    # JDBC implementation
│   ├── ui/ConsoleMenu.java        # Console menu / presentation layer
│   └── util/DBConnection.java     # JDBC connection helper
├── sample_run_output.txt          # Real captured output of a full CRUD run
└── .gitignore
```

## Setup

### 1. Create the database

```bash
mysql -u root -p < sql/schema.sql
```

This creates the `student_db` database and the `students` table.

### 2. Configure credentials

Edit `src/main/java/com/laya/sms/util/DBConnection.java` and set your MySQL
username/password:

```java
private static final String URL = "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

### 3. Get the MySQL JDBC driver

Download `mysql-connector-j` (e.g. `mysql-connector-j-8.3.0.jar`) from
https://dev.mysql.com/downloads/connector/j/ and place it in a `lib/` folder,
or add it as a Maven/Gradle dependency if you convert this to a build-tool
project:

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.3.0</version>
</dependency>
```

### 4. Compile & run

```bash
javac -cp "lib/mysql-connector-j-8.3.0.jar" -d out $(find src -name "*.java")
java -cp "out:lib/mysql-connector-j-8.3.0.jar" com.laya.sms.Main
```

On Windows, replace `:` with `;` in the classpath.

## Sample Output

This exact program was compiled and run end-to-end against a live MySQL
database (add → view → search → update → view → delete → view). The full,
real console transcript is captured in
[`sample_run_output.txt`](./sample_run_output.txt). Excerpt:

```
--------------------- MENU ---------------------
1. Add Student
2. View All Students
3. Update Student
4. Delete Student
5. Search Student by ID
0. Exit
--------------------------------------------------
Enter your choice:
-- All Students --
ID   Name                 Age   Course          Email
--------------------------------------------------------------------
1    Laya Sarilla         22    B.Tech ECE      laya@example.com
2    Rahul Kumar          21    Computer Science rahul@example.com
3    Sneha Reddy          23    Information Tech sneha@example.com
```

## Pushing to GitHub

```bash
cd student-management-system
git init
git add .
git commit -m "Initial commit: Student Management System (Java + JDBC + MySQL)"
git branch -M main
git remote add origin https://github.com/<your-username>/student-management-system.git
git push -u origin main
```

## Author

Laya Sarilla
