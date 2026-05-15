import java.util.Scanner;
class Item{

    String name;
    double price;
    int quantity;           // if we don't call the constructor then this will return 0, 0.0 and null values
    String type;

    Item(String n, double p, int q, String t){
        name = n;
        price = p;                                     // this is parametric constructor
        quantity = q;                                  // name is instance variable and n is parameter variable
        type = t;
    }

    double calculateTax(){

        double tax = 0;
        switch(type.toLowerCase()){
            case "raw":
                tax = 0.125 * price;
                break;
            case "manufactured":
                double basicTax = 0.125* price;
                tax = basicTax + (0.02*(price + basicTax));
                break;
            case "imported":
                tax = 0.1* price;
                double finalCost = price + tax;
                if(finalCost <= 100){
                    tax = tax + 5;
                }
                else if(finalCost <= 200){
                    tax = tax + 10;
                }
                else{
                    tax = tax + (0.05*finalCost);
                }
                break;
            default:
                System.out.println("Invalid item type");
                break;
        }
        return tax;
    }

    void display(){             // display() is method name with void return type with no parameters 
        double tax = calculateTax();
        double finalPrice = price + tax;
        System.out.println("Item:" + name );
        System.out.println("Price:" + price);
        System.out.println("Quantity:" + quantity);
        System.out.println("Type:" + type);
        System.out.println("Tax:" + tax);
        System.out.println("Final Price:" + finalPrice);

    }
}
public class Assignment1{                            //only one public class in one file
    public static void main(String[] args){          // main() is method name and starting point of program execution

        Scanner sc = new Scanner(System.in);           // Scanner is predefined class and sc is object name it can also be s,ab or anything else
        char choose;
        do{
            try{
                System.out.print("Enter item name:");
                String name = sc.nextLine();

                System.out.print("Enter item price:");
                double price = sc.nextDouble();

                System.out.print("Enter item quantity:");
                int quantity = sc.nextInt();
                
                sc.nextLine();

                System.out.print("Enter item type (raw/manufactured/imported):");
                String type = sc.nextLine();

                Item item = new Item(name, price, quantity, type);
                item.display();
            }
            catch(NumberFormatException e){
                System.out.println("Invalid number format. Please try again.");
                sc.nextLine();
            }
            catch(Exception e){
                System.out.println("Invalid input. Please try again.");
                sc.nextLine();
            }
            System.out.println("Do you want to enter details of another item? (y/n):");
            choose = sc.next().charAt(0);
            sc.nextLine();
        }
        while(choose == 'y' || choose == 'Y');
        sc.close();
    }

}