//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static Scanner scanner; // Note: Do not change this line.
    private static final int MAX_STUDENTS = 100;
    // List of student names
    private static String[] names = new String[MAX_STUDENTS];
    // Use a linked hashmap to maintain order when displaying students
    private static LinkedHashMap<String, List<Double>> studentGrades = new LinkedHashMap<>();
    // keep track of how many students in the system
    private static int studentCount = 0;

    // Add a new student to the system
    // Accepts a student name and a list of their grades as parameters
    public static void newStudent(String name, List<Double> grades) {
        // check if limit reached
        if (studentCount > MAX_STUDENTS) {
            System.out.println("Student limit reached.");
            return;
        }

        // check if grades are valid
        for (Double grade : grades) {
            if (grade < 0 || grade > 100) {
                System.out.println("Invalid grades.");
                return;
            }
        }

        // if student in system already update grades
        // else, enter student in hashmap
        if (studentGrades.containsKey(name)) {
            studentGrades.put(name, grades);
        }
        else {
            names[studentCount] = name;
            studentCount++;
            studentGrades.put(name, grades);
        }
        System.out.println("Student " + name + " added successfully!");
    }

    // Display all students in the system
    public static void displayStudents() {
        // check if grades system is empty
        if (studentCount == 0) {
            System.out.println("No student records available.");
            return;
        }
        // If not empty, display all students with their grades.
        // Grades displayed as string
        for (String student : studentGrades.keySet()) {
            List<Double> grades = studentGrades.get(student);
            String gradesString = "";
            for (int i = 0; i < grades.size(); i++) {
                gradesString += String.format("%.2f", grades.get(i));
                if (i < grades.size() - 1) {
                    gradesString += ", ";
                }
            }
            System.out.println("Name: " + student + ", Grades: " + gradesString);
        }
    }

    // Average of a student
    public static void displayAverage(String name) {
        // check if the student provided is in the system
        // if yes, convert List to stream, convert each Double to a double, and average
        // then display name and average
        // else, "No student found..."
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
        // check if system is empty
        if (studentCount == 0) {
            System.out.println("No student records available.");
            return;
        }

        // find student with the highest average and display
        String bestStudent = null;
        double highestAverage = 0;

        for (String student : studentGrades.keySet()) {
            List<Double> grades = studentGrades.get(student);
            double average = grades.stream().mapToDouble(Double::doubleValue).average().orElse(0);

            if (average > highestAverage) {
                highestAverage = average;
                bestStudent = student;
            }
        }
        if (bestStudent != null) {
            System.out.println("Top performing student: " + bestStudent + " with an average grade of "
                    + String.format("%.2f", highestAverage));
        }
    }
    /* Implementation of grade management system.
    * Methods include - adding new student, displaying all students,
    * displaying the student with the highest average, and
    * displaying the average of a specific student. */
    public static void manageGrades(Scanner scanner) {
        // Reset array if needed
        studentGrades.clear();
        studentCount = 0;

        System.out.println("Welcome to the Student Grade Management System!");
        // loop until user exits
        while (scanner.hasNext()) {
            try {
                System.out.println("1. Add a new student\n" +
                        "2. Display all students\n" +
                        "3. Calculate a student's average grade\n" +
                        "4. Find the top performing student\n" +
                        "5. Exit");
                System.out.println("Please enter your choice: ");

                // receive option from user
                int option = scanner.nextInt();
                scanner.nextLine(); // absorb new line character

                if (option == 5) {
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                } else if (option == 1) {
                    System.out.println("Enter student name: ");
                    String name = scanner.nextLine().trim(); // enter student name

                    List<Double> grades = new ArrayList<>();
                    System.out.println("Enter grades: ");
                    String gradeInput = scanner.nextLine().trim(); // enter student grades
                    String[] gradeStrings = gradeInput.split("\\s+");

                    // check that all grades are valid inputs
                    boolean gradesValid = true;
                    for (String gradeString : gradeStrings) {
                        try {
                            double grade = Double.parseDouble(gradeString);
                            grades.add(grade);
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter valid numbers.");
                            gradesValid = false;
                        }
                    }
                    if (gradesValid) {
                        newStudent(name, grades);
                    }
                } else if (option == 2) {
                    displayStudents();
                } else if (option == 3) {
                    System.out.println("Enter student name: ");
                    String student = scanner.nextLine();
                    displayAverage(student);
                } else if (option == 4) {
                    bestStudent();
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid choice. Please try again.");
                scanner.next(); // absorb new line character
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String path = args[0];
        scanner = new Scanner(new File(path));
        int numberOfTests = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numberOfTests; i++) {
            System.out.println("Test number " + i + " starts.");
            try {
                manageGrades(scanner);
            } catch(Exception e){
                System.out.println("Exception " + e);
            }
            System.out.println("Test number " + i + " ended.");
            System.out.println("-----------------------------------------------");
        }
        System.out.println("All tests have ended.");
    }
}