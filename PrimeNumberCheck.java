package BaiTapLession14;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrimeNumberCheck {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n ;
            while (true)
                try {
                  System.out.print("Nhập một số nguyên dương: ");
                  n = Integer.parseInt(scanner.nextLine());
                  if (n <= 0) {
                      System.out.println("Lỗi: Số phải lớn hơn 0 để kiểm tra số nguyên tố.");
                  } else {
                      if (isPrime(n)) {
                          System.out.println(n + " là số nguyên tố.");
                      } else {
                          System.out.println(n + " không phải là số nguyên tố.");
                      }
                      break;
                  }

              } catch (InputMismatchException e) {
                  System.out.println("Lỗi: Bạn phải nhập một số nguyên hợp lệ.");

              }
          }

        public static boolean isPrime(int n) {
            if (n < 2) return false;

            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }


