//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static Scanner scanner; // Note: Do not change this line.

    private static int maxStudents = 100;
    // List of student names
    private static String[] names = new String[maxStudents];
    // Hashmap of students and their grades as key-value pairs
    private static HashMap<String, List<Double>> studentGrades = new HashMap<>();
    // keep track of how many students in the system
    private static int studentCount = 0;

    public static void manageGrades() {
        // Reset array if needed
        studentGrades.clear();
        studentCount = 0;
    }

    // Add a new student to the system
    // Accepts a student name and a list of their grades as parameters
    public static void newStudent(String name, List<Double> grades) {
        // limit reached check
        if (studentCount > maxStudents) {
            System.out.println("Student limit reached.");
            return;
        }

        // valid grade check
        for (Double grade : grades) {
            if (grade < 0 || grade > 100) {
                System.out.println("Invalid grades.");
                return;
            }
        }
        // enter student with grades into HashMap
        names[studentCount] = name;
        studentCount++;
        studentGrades.put(name, grades);
        System.out.println("Student " + name + "added successfully!");
    }

    // Display all students in the system
    public static void displayStudents() {
        if (studentCount == 0) {
            System.out.println("No student records available.");
            return;
        }
        for (String student : studentGrades.keySet()) {
            System.out.println("Name: " + student + ", Grades: " + studentGrades.get(student));
        }
    }

    // Average of a student
    public static void displayAverage(String name) {
        if (studentGrades.containsKey(name)) {
            List<Double> grades = studentGrades.get(name);
            double average = grades.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            System.out.println("Average grade for " + name + ": " + String.format("%.2f", average));
        }
        else {
            System.out.println("No student found with name " + name + ".");
        }
    }

    // Top performing student
    public static void bestStudent() {
        if (studentCount == 0) {
            System.out.println("No student records available.");
            return;
        }

        String best = "";
        double avg = 0.0;

        for (String student : studentGrades.keySet()) {
            List<Double> grades = studentGrades.get(student);
            double average = grades.stream().mapToDouble(Double::doubleValue).average().orElse(0);

            if (average > avg) {
                avg = average;
                best = studentGrades.get(student).toString();
            }
        }

        System.out.println("Top performing student: " + best + "with an average of " + String.format("%.2f", avg));
    }


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        manageGrades();

        System.out.println("Welcome to the Student Grade Management System!");

        while (true) {
            System.out.println("1. Add a new student\n" +
                    "2. Display all students\n" +
                    "3. Calculate a student's average grade\n" +
                    "4. Find the top performing student\n" +
                    "5. Exit");
            System.out.print("Please enter your choice: ");

            int option = scanner.nextInt();
            scanner.nextLine();
            if (option == 5) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            } else if (option == 1) {
                System.out.println("Enter student name: ");
                String name = scanner.nextLine();

                List<Double> grades = new ArrayList<>();
                System.out.println("Enter grades: ");
                String gradeInput = scanner.nextLine();

                try {
                    double grade = Double.parseDouble(gradeInput);
                    grades.add(grade);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter valid numbers.");
                }

                newStudent(name, grades);
            } else if (option == 2) {
                displayStudents();
            } else if (option == 3) {
                System.out.println("Enter student name: ");
                String student = scanner.nextLine();
                displayAverage(student);
            } else if (option == 4) {
                bestStudent();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }


        String path = args[0];
        scanner = new Scanner(new File(path));
        int numberOfTests = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numberOfTests; i++) {
            System.out.println("Test number " + i + " starts.");
            try {
                manageGrades();
            } catch(Exception e){
                System.out.println("Exception " + e);
            }
            System.out.println("Test number " + i + " ended.");
            System.out.println("-----------------------------------------------");
        }
        System.out.println("All tests have ended.");
    }
}