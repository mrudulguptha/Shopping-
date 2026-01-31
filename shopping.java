import java.util.*;

public class OnlineShoppingSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static Map<String, Double> productCatalog = new LinkedHashMap<>();
    private static Map<String, Integer> cart = new LinkedHashMap<>();

    public static void main(String[] args) {
        setupProducts();

        System.out.println("\n🛍️ Welcome to DesiCart - India's Swag Wala Shopping Experience 🇮🇳✨");

        System.out.print("\n📱 Enter your mobile number to receive OTP: ");
        String mobile = scanner.nextLine();

        if (!mobile.matches("\\d{10}")) {
            System.out.println("❌ Invalid mobile number. Please try again.");
            return;
        }

        if (!verifyOTP(mobile)) {
            System.out.println("❌ OTP verification failed. Try again later.");
            return;
        }

        int choice;
        do {
            showMenu();
            System.out.print("Enter your choice (1-6): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: viewProducts(); break;
                case 2: addToCart(); break;
                case 3: viewCart(); break;
                case 4: removeItem(); break;
                case 5: checkout(); break;
                case 6: System.out.println("\n🙏 Thank you for shopping with DesiCart! Come again! 🧡"); break;
                default: System.out.println("⚠️ Invalid choice. Try again.");
            }
        } while (choice != 6);
    }

    private static boolean verifyOTP(String mobile) {
        Random rand = new Random();
        int otp = 1000 + rand.nextInt(9000);
        System.out.println("\n🔒 OTP sent to " + mobile.substring(0, 6) + "****");
        System.out.println("(Simulation: Your OTP is " + otp + ")");
        System.out.print("Enter OTP to verify: ");
        int enteredOtp = scanner.nextInt();
        scanner.nextLine();
        return enteredOtp == otp;
    }

    private static void setupProducts() {
        productCatalog.put("Tata Salt (1kg)", 30.0);
        productCatalog.put("Amul Milk (500ml)", 28.0);
        productCatalog.put("Parle-G Biscuits", 10.0);
        productCatalog.put("Maggi Noodles", 15.0);
        productCatalog.put("Dettol Soap", 35.0);
        productCatalog.put("Tropicana Juice", 60.0);
    }

    private static void showMenu() {
        System.out.println("\n========= DesiCart Menu =========");
        System.out.println("1. 🛒 View Products");
        System.out.println("2. ➕ Add to Cart");
        System.out.println("3. 🧺 View Cart");
        System.out.println("4. ❌ Remove from Cart");
        System.out.println("5. 💳 Checkout");
        System.out.println("6. 🚪 Exit");
        System.out.println("=================================");
    }

    private static void viewProducts() {
        System.out.println("\n📦 Available Products:");
        int i = 1;
        for (Map.Entry<String, Double> entry : productCatalog.entrySet()) {
            System.out.println(i + ". " + entry.getKey() + " - ₹" + entry.getValue());
            i++;
        }
    }

    private static void addToCart() {
        viewProducts();
        System.out.print("\nEnter product number to add: ");
        int productNum = scanner.nextInt();
        scanner.nextLine();

        if (productNum < 1 || productNum > productCatalog.size()) {
            System.out.println("❌ Invalid selection.");
            return;
        }

        String selectedProduct = (new ArrayList<>(productCatalog.keySet())).get(productNum - 1);
        System.out.print("Enter quantity: ");
        int qty = scanner.nextInt();
        scanner.nextLine();

        cart.put(selectedProduct, cart.getOrDefault(selectedProduct, 0) + qty);
        System.out.println("✅ " + qty + " x " + selectedProduct + " added to cart.");
    }

    private static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("\n🛒 Your cart is empty.");
            return;
        }
        System.out.println("\n🧺 Your Cart:");
        double total = 0.0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            double price = productCatalog.get(entry.getKey());
            double subtotal = price * entry.getValue();
            total += subtotal;
            System.out.println("- " + entry.getKey() + " x " + entry.getValue() + " = ₹" + subtotal);
        }
        System.out.println("Total Amount: ₹" + total);
    }

    private static void removeItem() {
        if (cart.isEmpty()) {
            System.out.println("\n❌ Your cart is empty.");
            return;
        }
        viewCart();
        System.out.print("\nEnter product name to remove: ");
        String item = scanner.nextLine();
        if (cart.containsKey(item)) {
            cart.remove(item);
            System.out.println("🗑️ " + item + " removed from cart.");
        } else {
            System.out.println("❌ Item not found in cart.");
        }
    }

    private static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("\n🛒 Your cart is empty. Add some items before checkout.");
            return;
        }
        viewCart();
        System.out.print("\n💳 Proceed to payment? (yes/no): ");
        String confirm = scanner.nextLine().toLowerCase();
        if (confirm.equals("yes")) {
            System.out.println("\n✅ Payment Successful! Your order is on its way! 🚚📦");
            cart.clear();
        } else {
            System.out.println("\n⚠️ Payment Cancelled.");
        }
    }
}