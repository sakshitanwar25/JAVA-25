package Assignment_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StudentManager {

    ArrayList<Student> students;
    StudentManager(ArrayList<Student> s) {

        students = s;
    }

    // Add student
    void addStudent(Scanner sc) {

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        if (name.isEmpty()) {

            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        // Check duplicate roll number
        for (Student s : students) {

            if (s.rollNumber == roll) {

                System.out.println("Roll Number already exists.");
                return;
            }
        }

        ArrayList<String> courses =
                new ArrayList<>();

        System.out.println("Choose 4 courses from A B C D E F");

        for (int i = 1; i <= 4; i++) {

            System.out.print("Enter Course " + i + ": ");

            String course = sc.nextLine();

            courses.add(course);
        }

        Student s =
                new Student(name, age, address,
                            roll, courses);

        students.add(s);

        Collections.sort(students);
        System.out.println("Student Added Successfully.");
    }

    // Displaying students
    void displayStudents(Scanner sc) {

        if (students.isEmpty()) {

            System.out.println("No students found.");
            return;
        }

        System.out.println("Sort By:");
        System.out.println("1. Name");
        System.out.println("2. Roll Number");
        System.out.println("3. Age");
        System.out.println("4. Address");

        int choice = sc.nextInt();

        System.out.println("1. Ascending");
        System.out.println("2. Descending");

        int order = sc.nextInt();

        // For sorting using comparator
        switch (choice) {

            case 1:

                Collections.sort(students,
                        Comparator.comparing(a -> a.name));
                break;

            case 2:

                Collections.sort(students,
                        Comparator.comparingInt(a -> a.rollNumber));
                break;

            case 3:

                Collections.sort(students,
                        Comparator.comparingInt(a -> a.age));
                break;

            case 4:

                Collections.sort(students,
                        Comparator.comparing(a -> a.address));
                break;

            default:

                System.out.println("Invalid Choice.");
                return;
        }

        // In descending order
        if (order == 2) {

            Collections.reverse(students);
        }

        System.out.println(
                "Name\tRoll\tAge\tAddress\tCourses");

        for (Student s : students) {

            System.out.println(s);
        }
    }

    //For deleting student 
    void deleteStudent(Scanner sc) {

        System.out.print("Enter Roll Number: ");

        int roll = sc.nextInt();

        Student found = null;

        for (Student s : students) {

            if (s.rollNumber == roll) {

                found = s;
                break;
            }
        }

        if (found != null) {

            students.remove(found);

            System.out.println("Student deleted.");

        } else {

            System.out.println("Roll Number not found.");
        }
    }

    // It will return student list
    ArrayList<Student> getStudents() {

        return students;
    }
}