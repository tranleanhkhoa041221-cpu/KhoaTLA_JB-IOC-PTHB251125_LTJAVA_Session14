package Model;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Product implements PBaseModel {
    private String id;
    private String name;
    private double price;

    public Product() {
    }

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void inputData(Scanner sc) {
        System.out.println("--- Nhập thông tin sản phẩm ---");
        while (true) {
            System.out.print("Nhập tên sản phẩm: ");
            name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Lỗi: Tên sản phẩm không được để trống!");
            } else {
                break;
            }
        }
        while (true) {
            System.out.print("Nhập giá sản phẩm: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Lỗi: Giá không được để trống!");
                continue;
            }
            try {
                price = Double.parseDouble(input);
                if (price <= 0) {
                    System.out.println("Lỗi: Giá phải lớn hơn 0!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Giá sản phẩm không hợp lệ (Phải là số)!");
            }
        }
    }
       @Override
        public void displayData() {
            System.out.printf("| %-6s | %-20s | %-12.2f |\n", id, name, price);
        }

        @Override
        public String toString() {
            return String.format("%s - %s - %.2f VND", id, name, price);
        }
    }

