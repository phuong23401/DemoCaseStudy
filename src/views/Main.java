package views;

import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu() {
        while (true) {
            System.out.println("----- CHƯƠNG TRÌNH QUẢN LÍ SÁCH -----");
            System.out.println("Chọn chức năng theo số để tiếp tục");
            System.out.println("1. Quản lí thư viện");
            System.out.println("2. Khách hàng");
            System.out.println("0. Thoát");
            System.out.println("Mời chọn chức năng: ");

            int choose = Integer.parseInt(SCANNER.nextLine());
            switch (choose) {
                case 1:
                    MenuManager.showMenuManager();
                    break;
                case 2:
                    MenuCustomer.showMenuCustomer();
                    break;
                case 0:
                    System.out.println("Đã thoát!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Mời chọn chức năng trong menu!");
                    break;
            }
        }
    }
}
