package BaiTapLession14;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParseIntExample {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            List<Integer> validNumbers = new ArrayList<>();
            int invalidCount = 0;

            System.out.println("Nhập các chuỗi (gõ 'exit' để kết thúc):");

            while (true) {
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                try {
                    int number = Integer.parseInt(input);
                    validNumbers.add(number);
                } catch (NumberFormatException e) {
                    invalidCount++;
                }
            }

            System.out.println("Số chuỗi hợp lệ: " + validNumbers.size());
            System.out.println("Số chuỗi không hợp lệ: " + invalidCount);
            System.out.println("Danh sách số nguyên hợp lệ: " + validNumbers);

            scanner.close();
        }
    }


