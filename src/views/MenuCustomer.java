package views;

import controller.Library;
import model.Book;

import java.util.Scanner;

public class MenuCustomer {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static int id;

    public static void showMenuCustomer() {
        while (true) {
            System.out.println("----- CHƯƠNG TRÌNH QUẢN LÍ SÁCH -----");
            System.out.println("---- Khách hàng ----");
            System.out.println("Chọn chức năng theo số để tiếp tục");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Sắp xếp");
            System.out.println("3. Tìm kiếm");
            System.out.println("4. Thuê sách");
            System.out.println("0. Thoát");
            System.out.println("Mời chọn chức năng: ");

            int choose = Integer.parseInt(SCANNER.nextLine());
            switch (choose) {
                case 1:
                    Library.show();
                    break;
                case 2:
                    showMenuSort();
                    break;
                case 3:
                    showMenuSearch();
                    break;
                case 4:
                    System.out.println("//Giá thuê sách cũ: 50%");
                    System.out.println("//Giá thuê sách mới: 70%");
                    id = Library.inputId();
                    Library.leaseBook(id);
                    break;
                case 0:
                    Main.showMenu();
                    break;
                default:
                    System.out.println("Mời chọn chức năng trong menu!");
                    break;
            }
        }
    }

    public static void showMenuSort() {
        while (true) {
            System.out.println("----- CHƯƠNG TRÌNH QUẢN LÍ SÁCH -----");
            System.out.println("---- Khách hàng ----");
            System.out.println("Chọn chức năng theo số để tiếp tục");
            System.out.println("1. Sắp xếp theo giá tăng dần");
            System.out.println("2. Sắp xếp theo giá giảm dần");
            System.out.println("3. Sắp xếp theo tên");
            System.out.println("0. Thoát");
            System.out.println("Mới chọn chức năng: ");

            int choose = Integer.parseInt(SCANNER.nextLine());
            switch (choose) {
                case 1:
                    Library.sortByPriceUp();
                    break;
                case 2:
                    Library.sortByPriceDown();
                    break;
                case 3:
                    Library.sortByName();
                    break;
                case 0:
                    showMenuCustomer();
                    break;
                default:
                    System.out.println("Mời chọn chức năng trong menu!");
                    break;
            }
        }
    }

    public static void showMenuSearch() {
        while (true) {
            System.out.println("----- CHƯƠNG TRÌNH QUẢN LÍ SÁCH -----");
            System.out.println("---- Khách hàng ----");
            System.out.println("Chọn chức năng theo số để tiếp tục");
            System.out.println("1. Tìm kiếm theo id");
            System.out.println("2. Tìm kiếm theo tên");
            System.out.println("3. Hiển thị sách có giá đắt nhất");
            System.out.println("0. Thoát");
            System.out.println("Mới chọn chức năng: ");

            int choose = Integer.parseInt(SCANNER.nextLine());
            switch (choose) {
                case 1:
                    id = Library.inputId();
                    Library.searchById(id);
                    break;
                case 2:
                    String name = Library.inputName();
                    Library.searchByName(name);
                    break;
                case 3:
                    Book book = Library.showMaxPrice();
                    System.out.println(book);
                    break;
                case 0:
                    showMenuCustomer();
                    break;
                default:
                    System.out.println("Mời chọn chức năng trong menu!");
                    break;
            }
        }
    }
}
