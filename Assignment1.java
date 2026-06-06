import java.util.InputMismatchException;
import java.util.Scanner;

enum ItemType {
    RAW(0.125, 0.0, 0, 0),
    MANUFACTURED(0.125, 0.02, 0, 0),
    IMPORTED(0.1, 0.05, 100, 200);

    private final double basicRate; // 0.125 or 0.1
    private final double additionalRate; // 0.02 or 0.05
    private final double lowThreshold; // 100
    private final double highThreshold; // 200

    ItemType(double b, double a, double l, double h) {
        basicRate = b;
        additionalRate = a;
        lowThreshold = l;
        highThreshold = h;
    }

    public double getBasicRate() {
        return basicRate;
    }

    public double getAdditionalRate() {
        return additionalRate;
    }

    public double getLowThreshold() {
        return lowThreshold;
    }

    public double getHighThreshold() {
        return highThreshold;
    }
}

class Item {

    private String name;
    private double price;
    private int quantity; // if we don't call the constructor then this will return 0, 0.0 and null values
    private ItemType type;

    public Item(String n, double p, int q, ItemType t) {
        name = n;
        price = p; // this is parametric constructor
        quantity = q; // name is instance variable and n is parameter variable
        type = t;
    }

    public double calculateTax() {

        double tax = 0;
        switch (type) {

            case RAW:
                tax = type.getBasicRate() * price;
                break;
            case MANUFACTURED:
                double basicTax = type.getBasicRate() * price;
                tax = basicTax + (type.getAdditionalRate() * (price + basicTax));
                break;
            case IMPORTED:
                tax = type.getBasicRate() * price;
                double finalCost = price + tax;
                if (finalCost <= type.getLowThreshold()) {
                    tax += 5;
                } else if (finalCost <= type.getHighThreshold()) {
                    tax += 10;
                } else {
                    tax += type.getAdditionalRate() * finalCost;
                }
                break;
            default:
                System.out.println("Invalid item type");
                break;
        }
        return tax;
    }

    public void display() { // display() is method name with void return type with no parameters
        double tax = calculateTax();
        double finalPrice = price + tax;
        System.out.println("Item:" + name);
        System.out.println("Price:" + price);
        System.out.println("Quantity:" + quantity);
        System.out.println("Type:" + type);
        System.out.println("Tax:" + tax);
        System.out.println("Final Price:" + finalPrice);

    }
}

public class Assignment1 { // only one public class in one file
    public static void main(String[] args) { // main() is method name and starting point of program execution

        Scanner sc = new Scanner(System.in); 
        char choose;
        do {
            boolean validInput = false;
            while (!validInput) {
                try {
                    System.out.print("Enter item name:");
                    String name = sc.nextLine();

                    System.out.print("Enter item price:");
                    double price = sc.nextDouble();

                    System.out.print("Enter item quantity:");
                    int quantity = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter item type (raw/manufactured/imported):");
                    String typeInput = sc.nextLine()
                            .trim()
                            .toUpperCase();
                    ItemType type = ItemType.valueOf(typeInput);

                    Item item = new Item(name, price, quantity, type);
                    item.display();
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid type. Please enter: raw / manufactured / imported");
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid number. Please enter a valid price/quantity.");
                    sc.nextLine();
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    sc.nextLine();
                }
            }
            System.out.println("Do you want to enter details of another item? (y/n):");
            choose = sc.next().charAt(0);
            sc.nextLine();
        } while (choose == 'y' || choose == 'Y');
        sc.close();
    }

}