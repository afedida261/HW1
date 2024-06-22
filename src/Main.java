//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static Scanner scanner; // Note: Do not change this line.
    public static void manageGrades() {
        int maxStudents = 100;

        // Array housing student names
        String[] students = new String[maxStudents];

        // ArrayList of ArrayLists where the first index corresponds to that of student
        // and second list contains the students' grades
        ArrayList<ArrayList<Integer>> grades = new ArrayList<>(maxStudents);
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Student Grade Management System!");
        System.out.println("1. Add a new student\n" +
                "2. Display all students\n" +
                "3. Calculate a student's average grade\n" +
                "4. Find the top performing student\n" +
                "5. Exit");
        System.out.print("Please enter your choice: ");
        int choice = scanner.nextInt();





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