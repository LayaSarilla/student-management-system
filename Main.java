package com.laya.sms;

import com.laya.sms.ui.ConsoleMenu;

/**
 * Entry point for the Student Management System.
 * A console-based Java application to add, update, delete, and view
 * student records, using JDBC to connect to a MySQL database.
 */
public class Main {
    public static void main(String[] args) {
        new ConsoleMenu().start();
    }
}
