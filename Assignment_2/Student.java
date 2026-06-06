package Assignment_2;
import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable, Comparable<Student> {

    String name;
    int age;
    String address;
    int rollNumber;
    ArrayList<String> courses;

    Student(String n, int a, String add, int r, ArrayList<String> c) {

        name = n;
        age = a;
        address = add;
        rollNumber = r;
        courses = c;
    }
    @Override
    public int compareTo(Student s) {
        if (name.equalsIgnoreCase(s.name)) {
            return rollNumber - s.rollNumber;
        }
        return name.compareToIgnoreCase(s.name);
    }
    @Override
    public String toString() {

        return name + "\t" +
               rollNumber + "\t" +
               age + "\t" +
               address + "\t" +
               courses;
    }
}
