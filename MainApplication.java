package Presentation;

import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        run(input);
    }
    public static void run(Scanner input){
        ShopView.showMenu(input);
    }
}
