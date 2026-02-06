package Business.impl;

import Model.CartItem;
import Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
        private List<CartItem> cartItems = new ArrayList<>();

        public void addToCart(Product product, int quantity) {
            for (CartItem item : cartItems) {
                if (item.getProduct().getId().equals(product.getId())) {
                    item.setQuantity(item.getQuantity() + quantity);
                    System.out.println("Đã cập nhật số lượng sản phẩm trong giỏ!");
                    return;
                }
            }

            cartItems.add(new CartItem(product, quantity));
            System.out.println("Đã thêm sản phẩm vào giỏ hàng thành công");
        }

        public void removeFromCart(String productId) {
            CartItem itemToRemove = null;
            for (CartItem item : cartItems) {
                if (item.getProduct().getId().equalsIgnoreCase(productId)) {
                    itemToRemove = item;
                    break;
                }
            }

            if (itemToRemove != null) {
                cartItems.remove(itemToRemove);
                System.out.println("Đã xóa sản phẩm khỏi giỏ hàng.");
            } else {
                throw new IllegalArgumentException("Lỗi: Không tìm thấy sản phẩm trong giỏ hàng!");
            }
        }

        public List<CartItem> getCartItems() {
            return cartItems;
        }

        public double checkout() {
            double total = 0;
            for (CartItem item : cartItems) {
                total += item.getSubtotal();
            }
            return total;
        }

        public void clearCart() {
            cartItems.clear();
        }
    }

