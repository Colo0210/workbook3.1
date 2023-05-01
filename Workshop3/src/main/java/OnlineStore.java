import java.io.*;
import java.util.*;

public class OnlineStore {

    private static final String INVENTORY_FILE_PATH = "src/main/inventory.csv";

    private Map<String, Product> inventory = new HashMap<>();
    private Map<Product, Integer> cart = new HashMap<>();

    public static void main(String[] args) {
        OnlineStore onlineStore = new OnlineStore();
        onlineStore.loadInventory();
        onlineStore.run();
    }

    private void loadInventory() {
        {
            try (BufferedReader br = new BufferedReader(new FileReader("src/main/inventory.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] productData = line.split("\\|");
                    String productId = productData[0];
                    String productName = productData[1];
                    double productPrice = Double.parseDouble(productData[2]);
                    Product product = new Product(productId, productName, productPrice);
                    inventory.put(productId, product);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Inventory file not found.");
            } catch (IOException e) {
                System.out.println("Error reading inventory file.");
            }
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome to our online store! What would you like to do?");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    showProductsScreen(scanner);
                    break;
                case "2":
                    showCartScreen();
                    break;
                case "3":
                    checkout();
                    break;
                case "4":
                    System.out.println("Thank you for shopping with us. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void showProductsScreen(Scanner scanner) {
        System.out.println("Inventory:");
        for (Product product : inventory.values()) {
            System.out.printf("%s - %s - $%.2f%n", product.getId(), product.getName(), product.getPrice());
        }

        System.out.println("Enter product ID to add to cart, or X to go back to main screen:");
        String productId = scanner.nextLine().trim();
        if (productId.equalsIgnoreCase("X")) {
            return;
        }
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println("Invalid product ID. Please try again.");
            return;
        }
        System.out.println("Enter quantity to add to cart:");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero. Please try again.");
            return;
        }
        addProductToCart(product, quantity);
        System.out.println("Product added to cart.");
    }

    private void addProductToCart(Product product, int quantity) {
        int currentQuantity = cart.getOrDefault(product, 0);
        cart.put(product, currentQuantity + quantity);
    }

    private void showCartScreen() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            double total = 0;
            System.out.println("Cart:");
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                double itemTotal = product.getPrice() * quantity;
                System.out.printf("%s - %d - $%.2f - $%.2f%n", product.getName(), quantity, product.getPrice(), itemTotal);
                total += itemTotal;
            }
            System.out.printf("Total: $%.2f%n", total);
        }
    }

    private void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        double total = 0;
        System.out.println("Cart:");
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double itemTotal = product.getPrice() * quantity;
            System.out.printf("%s - %d - $%.2f - $%.2f%n", product.getName(), quantity, product.getPrice(), itemTotal);
            total += itemTotal;
        }
        System.out.printf("Total: $%.2f%n", total);

        System.out.println("Enter your name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.println("Enter your email address:");
        String email = scanner.nextLine();

        System.out.println("Enter your shipping address:");
        String shippingAddress = scanner.nextLine();

        System.out.println("Enter your credit card number:");
        String creditCardNumber = scanner.nextLine();

        System.out.println("Enter the expiration date (MM/YY):");
        String expirationDate = scanner.nextLine();

        System.out.println("Thank you for your order, " + name + "! Your items will be shipped to " + shippingAddress + " soon.");
        cart.clear();
    }
}



