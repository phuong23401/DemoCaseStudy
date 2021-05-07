package views;

import controller.Library;
import storage.IOFile;

import java.sql.PreparedStatement;
import java.util.Scanner;

public class MenuManager {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Library PHUONGDZ = new Library();

    public static void showMenuManager() {
        while (true) {
            System.out.println("----- CHƯƠNG TRÌNH QUẢN LÍ SÁCH -----");
            System.out.println("---- Quản lí thư viện ----");
            System.out.println("Chọn chức năng theo số để tiếp tục");
            System.out.println("1. Xem danh sách sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Sửa");
            System.out.println("4. Xóa");
            System.out.println("5. Đọc từ file");
            System.out.println("6. Ghi vào file");
            System.out.println("0. Thoát");
            System.out.println("Mời chọn chức năng: ");

            int choose = Integer.parseInt(SCANNER.nextLine());
            int id;
            switch (choose) {
                case 1:
                    PHUONGDZ.show();
                    break;
                case 2:
                    PHUONGDZ.add();
                    break;
                case 3:
                    id = PHUONGDZ.inputId();
                    PHUONGDZ.edit(id);
                    break;
                case 4:
                    id = PHUONGDZ.inputId();
                    PHUONGDZ.delete(id);
                    break;
                case 5:
                    IOFile.readBookList();
                    break;
                case 6:
                    IOFile.writeBookList();
                    System.out.println("Đã ghi vào file!");
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
}
