import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShopController {

    private List<Product> products = new ArrayList<>();
    private Cart cart = new Cart();
    private Scanner sc;

    public ShopController(Scanner sc) {
        this.sc = sc;

        products.add(new Product("Tata Salt", 30));
        products.add(new Product("Amul Milk", 28));
        products.add(new Product("Parle-G", 10));
        products.add(new Product("Maggi", 15));
    }

    public void startShopping() {
        showProducts();
        addProductsToCart();
        proceedToPayment();
    }

    private void showProducts() {
        System.out.println("\n--- Products ---");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " +
                    products.get(i).getName() +
                    " - ₹" + products.get(i).getPrice());
        }
    }

    private void addProductsToCart() {
        String choice = "yes";

        while (choice.equalsIgnoreCase("yes")) {

            System.out.print("\nSelect product number: ");
            int productNo = sc.nextInt();
            sc.nextLine();

            if (productNo < 1 || productNo > products.size()) {
                System.out.println("Invalid product number.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();
            sc.nextLine();

            cart.addProduct(products.get(productNo - 1), qty);

            System.out.print("Add more products? (yes/no): ");
            choice = sc.nextLine();
        }
    }

    private void proceedToPayment() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Order cancelled.");
            return;
        }

        cart.viewCart();

        System.out.print("\nProceed to payment? (yes/no): ");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            Order order = new Order(cart);
            order.placeOrder();
        } else {
            System.out.println("Payment cancelled.");
        }
    }
}
