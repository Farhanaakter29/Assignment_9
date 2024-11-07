
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class StudentRecords {
    static List<Student> studentList = new ArrayList<>();
    public static void main(String[] args) {
        String csvFilePath = "students.csv";  // Replace with the path to your CSV file
        readCSV(csvFilePath);

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Records Menu ---");
            System.out.println("1. Search Student by Roll Number");
            System.out.println("2. Update Student Record");
            System.out.println("3. Delete Student Record");
            System.out.println("4. Display All Records");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll Number to search: ");
                    String rollNumber = sc.nextLine();
                    searchStudent(rollNumber);
                    break;
                case 2:
                    System.out.print("Enter Roll Number to update: ");
                    String rollNumToUpdate = sc.nextLine();
                    updateStudent(rollNumToUpdate, sc);
                    break;
                case 3:
                    System.out.print("Enter Roll Number to delete: ");
                    String rollNumToDelete = sc.nextLine();
                    deleteStudent(rollNumToDelete);
                    break;
                case 4:
                    displayAllRecords();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }

    // Read CSV file and load student data into studentList
    public static <BufferedReader> void readCSV(String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while (null != (line = String.valueOf(br.getClass()))) {
                String[] data = line.split(",");
                studentList.add(new Student(data[0], data[1], Integer.parseInt(data[2])));
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }

    // Search for a student by roll number
    public static void searchStudent(String rollNumber) {
        for (Student student : studentList) {
            if (student.rollNumber.equals(rollNumber)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // Update a student's record by roll number
    public static void updateStudent(String rollNumber, Scanner sc) {
        for (Student student : studentList) {
            if (student.rollNumber.equals(rollNumber)) {
                System.out.println("Existing Record: " + student);
                System.out.print("Enter new name: ");
                student.name = sc.nextLine();
                System.out.print("Enter new marks: ");
                student.marks = sc.nextInt();
                System.out.println("Record updated.");
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // Delete a student record by roll number
    public static void deleteStudent(String rollNumber) {
        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.rollNumber.equals(rollNumber)) {
                iterator.remove();
                System.out.println("Student with Roll Number " + rollNumber + " deleted.");
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // Display all student records
    public static void displayAllRecords() {
        if (studentList.isEmpty()) {
            System.out.println("No student records available.");
        } else {
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }
}
