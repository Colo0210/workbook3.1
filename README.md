# Online Store
This is a Java program for an online store that allows users to view products, add them to a cart, and checkout. The program loads the inventory of products from a CSV file located at src/main/inventory.csv.

# Prerequisites
* Java 8 or higher
* Maven

# How to Run
* Clone this repository.
* Navigate to the project directory in the terminal.
* Run mvn clean install to compile and package the program.
* Run java -jar target/OnlineStore-1.0-SNAPSHOT.jar to start the program.

# Features
* Show products: Displays a list of all available products with their IDs, names, and prices. Users can choose to add a product to their cart or return to the main screen.
* Show cart: Displays the contents of the user's cart and the total cost. If the cart is empty, a message will be displayed instead.
* Checkout: Guides the user through the checkout process, including entering personal and payment information.

# Code Overview
* OnlineStore: The main class that contains the main method and the program's logic.
* Product: A class that represents a product and stores its ID, name, and price.
* loadInventory(): A method in OnlineStore that reads the CSV file and creates Product objects to populate the inventory map.
* showProductsScreen(Scanner scanner): A method in OnlineStore that displays the products and prompts the user to select a product and quantity to add to their cart.
* addProductToCart(Product product, int quantity): A method in OnlineStore that adds a specified quantity of a product to the user's cart.
* showCartScreen(): A method in OnlineStore that displays the contents of the user's cart and the total cost.
* checkout(): A method in OnlineStore that guides the user through the checkout process, including entering personal and payment information. After completing the checkout, the cart is cleared.

#Photos
