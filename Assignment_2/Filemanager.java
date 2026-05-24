package Assignment_2;
import java.io.*;
import java.util.ArrayList;

public class Filemanager {

    static String fileName = "students.dat";

    static void saveData(ArrayList<Student> students) {

        try {

            FileOutputStream file =
                    new FileOutputStream(fileName);

            ObjectOutputStream out =
                    new ObjectOutputStream(file);

            out.writeObject(students);

            out.close();
            file.close();

            System.out.println("Data saved successfully.");

        } catch (Exception e) {

            System.out.println("Error while saving data.");
        }
    }

    static ArrayList<Student> loadData() {

        ArrayList<Student> students =
                new ArrayList<>();

        try {

            FileInputStream file =
                    new FileInputStream(fileName);

            ObjectInputStream in =
                    new ObjectInputStream(file);

            students =
                    (ArrayList<Student>) in.readObject();

            in.close();
            file.close();

            System.out.println("Old data loaded.");

        } catch (Exception e) {

            System.out.println("No previous data found.");
        }

        return students;
    }
}