package Assignment_2;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Student> students =
                Filemanager.loadData();

        StudentManager manager =
                new StudentManager(students);

        int choice;

        do {

            System.out.println("-----MENU-----");

            System.out.println("1. Add User");
            System.out.println("2. Display Users");
            System.out.println("3. Delete User");
            System.out.println("4. Save Users");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    manager.addStudent(sc);
                    break;

                case 2:
                    manager.displayStudents(sc);
                    break;

                case 3:
                    manager.deleteStudent(sc);
                    break;

                case 4:
                    Filemanager.saveData(
                            manager.getStudents());
                    break;

                case 5:

                    System.out.print(
                            "Do you want to save changes? (yes/no): ");

                    String ans = sc.nextLine();

                    if (ans.equalsIgnoreCase("yes")) {

                        Filemanager.saveData(
                                manager.getStudents());
                    }

                    System.out.println("Program Closed.");
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}

// finished