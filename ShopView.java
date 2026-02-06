package Presentation;

import Business.IProductService;
import Business.impl.ProductServiceImpl;
import Business.impl.ShoppingCart;
import Model.CartItem;
import Model.Product;
import Model.Student;

import java.util.List;
import java.util.Scanner;

public class ShopView {

        private static final IProductService productService = new ProductServiceImpl();
        private static final ShoppingCart shoppingCart = new ShoppingCart();


        public static void showMenu(Scanner scanner) {
            while (true) {
                System.out.println("\n================ MENU ================");
                System.out.println("1. Xem danh sách sản phẩm ");
                System.out.println("2. Thêm sản phẩm vào kho ");
                System.out.println("3. Thêm sản phẩm vào giỏ hàng");
                System.out.println("4. Xem giỏ hàng");
                System.out.println("5. Xóa sản phẩm khỏi giỏ hàng");
                System.out.println("6. Thanh toán");
                System.out.println("7. Thoát");
                System.out.println("==============================================");
                System.out.print("Lựa chọn của bạn: ");
                try {
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            showProductList();
                            break;
                        case 2:
                            addNewProductToShop(scanner);
                            break;
                        case 3:
                            addProductToCart(scanner);
                            break;
                        case 4:
                            showCart();
                            break;
                        case 5:
                            removeProductFromCart(scanner);
                            break;
                        case 6:
                            doCheckout();
                            break;
                        case 7:
                            System.out.println("Thoát.");
                            return;
                        default:
                            System.out.println("Lựa chọn không hợp lệ!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi: Vui lòng nhập số!");
                } catch (Exception e) {
                    System.out.println("Đã xảy ra lỗi: " + e.getMessage());
                }
            }
        }

        private static void showProductList() {
            List<Product> list = productService.findAll();
            System.out.println("\n--- DANH SÁCH SẢN PHẨM ---");
            System.out.printf("+%s+%s+%s+\n", "-".repeat(8), "-".repeat(22), "-".repeat(14));
            System.out.printf("| %-6s | %-20s | %-12s |\n", "ID", "Tên Sản Phẩm", "Giá");
            System.out.printf("+%s+%s+%s+\n", "-".repeat(8), "-".repeat(22), "-".repeat(14));
            for (Product p : list) {
                p.displayData();
            }
            System.out.printf("+%s+%s+%s+\n", "-".repeat(8), "-".repeat(22), "-".repeat(14));
        }

    private static void addNewProductToShop(Scanner scanner) {
        System.out.println("Thêm sản phẩm mới");
        int n;
        System.out.println("Nhập số lượng sản phẩm muốn thêm: ");
        n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.printf("--- Nhập thông tin sản phẩm thứ %d ---\n", i + 1);
            Product product = new Product();
            product.inputData(scanner);
            productService.add(product);

        }
        System.out.printf("Đã thêm %d nhân viên thành công!\n", n);
    }

        private static void addProductToCart(Scanner scanner) {
            showProductList();

            System.out.print("Nhập ID sản phẩm muốn mua: ");
            String id = scanner.nextLine().trim().toUpperCase();

            Product product = productService.findById(id);
            if (product == null) {
                System.out.println("Lỗi: Không tìm thấy sản phẩm có ID: " + id);
                return;
            }

            while (true) {
                try {
                    System.out.println("Nhập số lượng mua: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    if (quantity <= 0) {
                        System.out.println("Lỗi: Số lượng phải lớn hơn 0!");
                    } else {
                        shoppingCart.addToCart(product, quantity);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi: Số lượng không hợp lệ (Phải là số)!");
                }
            }
        }

        private static void showCart() {
            List<CartItem> items = shoppingCart.getCartItems();
            if (items.isEmpty()) {
                System.out.println("Giỏ hàng đang trống!");
                return;
            }

            System.out.println("\n--- GIỎ HÀNG CỦA BẠN ---");
            System.out.printf("+%s+%s+%s+%s+%s+\n", "-".repeat(8), "-".repeat(22), "-".repeat(12), "-".repeat(10), "-".repeat(14));
            System.out.printf("| %-6s | %-20s | %-10s | %-8s | %-12s |\n", "ID", "Tên SP", "Đơn Giá", "SL", "Thành Tiền");
            System.out.printf("+%s+%s+%s+%s+%s+\n", "-".repeat(8), "-".repeat(22), "-".repeat(12), "-".repeat(10), "-".repeat(14));

            for (CartItem item : items) {
                item.displayCartItem();
            }
            System.out.printf("+%s+%s+%s+%s+%s+\n", "-".repeat(8), "-".repeat(22), "-".repeat(12), "-".repeat(10), "-".repeat(14));
            System.out.printf(">>> Tổng tiền thanh toán: %.2f VND\n", shoppingCart.checkout());
        }

        private static void removeProductFromCart(Scanner scanner) {
            showCart();
            if (shoppingCart.getCartItems().isEmpty()) {
                System.out.println("Giỏ hàng đang trống!");
                return;
            }

                System.out.print("Nhập ID sản phẩm muốn xóa khỏi giỏ: ");
                String id = scanner.nextLine().trim().toUpperCase();
                try {
                    shoppingCart.removeFromCart(id);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());

            }
        }

    private static void doCheckout() {
        if (shoppingCart.getCartItems().isEmpty()) {
            System.out.println("Giỏ hàng đang trống!");
            return;
        }

        double total = shoppingCart.checkout();
        System.out.printf("Xác nhận thanh toán hóa đơn trị giá: %,.0f VND? (Y/N): ", total);
        Scanner sc = new Scanner(System.in);
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            System.out.println("--- HÓA ĐƠN CHI TIẾT ---");
            showCart();
            shoppingCart.clearCart();
            System.out.println("Thanh toán hoàn tất. Dữ liệu đã được lưu hệ thống!");
        } else {
            System.out.println("Hủy thanh toán.");
        }
    }
}
