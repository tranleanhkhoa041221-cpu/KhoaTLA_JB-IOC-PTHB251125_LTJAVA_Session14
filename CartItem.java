package Model;

public class CartItem {
        private Product product;
        private int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
            return product.getPrice() * quantity;
        }

        public void displayCartItem() {
            System.out.printf("| %-6s | %-20s | %-10.0f | %-8d | %-12.2f |\n",
                    product.getId(), product.getName(), product.getPrice(), quantity, getSubtotal());
        }
    }

