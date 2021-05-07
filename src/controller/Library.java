package controller;

import model.Book;
import org.jetbrains.annotations.NotNull;
import storage.IOFile;

import java.util.*;

public class Library {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final List<Book> BOOK_LIST = new ArrayList<>();

    static  {
        Book b1 = new Book(1, "Tiếng Việt 1", "Sách giáo khoa", 30000, 10, "Mới");
        Book b2 = new Book(2, "Tiếng Việt 2", "Sách giáo khoa", 35000, 10, "Mới");
        Book b3 = new Book(3, "Toán 1", "Sách giáo khoa", 40000, 10, "Mới");
        Book b4 = new Book(4, "Toán 2", "Sách giáo khoa", 40000, 10, "Mới");
        Book b5 = new Book(5, "Doraemon", "Truyện tranh", 45000, 10, "Mới");
        BOOK_LIST.add(b1);
        BOOK_LIST.add(b2);
        BOOK_LIST.add(b3);
        BOOK_LIST.add(b4);
        BOOK_LIST.add(b5);
    }

    public void show() {
        for (Book book : BOOK_LIST) {
            System.out.println(book.toString());
        }
    }

    public void add() {
        while (true) {
            int id = inputId();
            if (checkDuplicateId(id)) {
                String name = inputName();
                String kindOfBook = inputKind();
                int price = inputPrice();
                int num = inputNumOfBook();
                String status = inputStatus();
                Book book = new Book(id, name, kindOfBook, price, num, status);
                BOOK_LIST.add(book);
                break;
            }
        }
        IOFile.writeBookList();
        System.out.println("Đã thêm sách vào thư viện!");
    }

    public void edit(int id) {
        Book book = getBookById(id);
        if (book != null) {
            book.setName(inputName());
            book.setKindOfBook(inputKind());
            book.setPrice(inputPrice());
            book.setNumOfBook(inputNumOfBook());
            book.setStatus(inputStatus());
            System.out.println("Đã sửa thông tin sách!");
        }
    }

    public void delete(int id) {
        Book book = getBookById(id);
        if (book != null) {
            BOOK_LIST.remove(book);
            System.out.println("Đã xóa sách!");
        }
    }

    public void searchById(int id) {
        Book book = getBookById(id);
        if (book != null)
            System.out.println(book);
    }

    public void searchByName(String name) {
        Book book = getBookByName(name);
        if (book != null) {
            System.out.println(book);
        }
    }

    public void sortByPriceUp() {
        BOOK_LIST.sort(new Comparator<Book>() {
            @Override
            public int compare(Book book1, Book book2) {
                if (book1.getPrice() > book2.getPrice()) {
                    return 1;
                }
                return -1;
            }
        });
        show();
    }

    public void sortByPriceDown() {
        BOOK_LIST.sort(new Comparator<Book>() {
            @Override
            public int compare(Book book1, Book book2) {
                if (book1.getPrice() < book2.getPrice()) {
                    return 1;
                }
                return -1;
            }
        });
        show();
    }

    public void sortByName() {
        BOOK_LIST.sort(new Comparator<Book>() {
            @Override
            public int compare(Book book1, Book book2) {
                return book1.getName().compareTo(book2.getName());
            }
        });
        show();
    }

    public Book showMaxPrice() {
        Book book = null;
        int maxPrice = 0;
        for (Book book1 : BOOK_LIST) {
            if (book1.getPrice() >= maxPrice) {
                maxPrice = book1.getPrice();
                book = book1;
            }
        }
        return book;
    }

    public void leaseBook(int id) {
        Book book = getBookById(id);
        if (book != null) {
            System.out.println("Sách bạn muốn thuê: " + book);
            int day = inputDay();
            int numLease = inputNumOfBook();
            int price = book.getPrice();
            int priceLease = day * numLease * price;

            priceLease = getPriceLease(book, priceLease);
            System.out.println("Giá tiền phải trả: " + priceLease);
            afterLease(book, numLease);
        }
    }

    private int getPriceLease(@NotNull Book book, int priceLease) {
        if (book.getStatus().equals("Cũ")) {
            priceLease = (int) (priceLease * 0.5);
        } else if (book.getStatus().equals("Mới")) {
            priceLease = (int) (priceLease * 0.7);
        }
        return priceLease;
    }

    private void afterLease(Book book, int numLease) {
        book.setNumOfBook(book.getNumOfBook() - numLease);
    }

    public boolean checkDuplicateId(int id) {
        for (Book book : BOOK_LIST) {
            if (book.getId() == id) {
                System.out.println("Trùng id. Mời nhập lại!");
                return false;
            }
        }
        return true;
    }

    public Book getBookById(int id) {
        Book book = null;
        for (Book book1 : BOOK_LIST) {
            if (book1.getId() == id) {
                book = book1;
                break;
            }
            System.out.println("Không tìm thấy sách với id trên!");
            break;
        }
        return book;
    }

    public Book getBookByName(String name) {
        Book book = null;
        for (Book book1 : BOOK_LIST) {
            if (book1.getName().equals(name)) {
                book = book1;
                break;
            }
            System.out.println("Không tìm thấy sách với tên trên!");
            break;
        }
        return book;
    }

    public int inputId() {
        System.out.print("Nhập id sách: ");
        while (true) {
            try {
                return Integer.parseInt(SCANNER.nextLine());
            } catch (Exception e) {
                System.out.println("Id không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public String inputName() {
        System.out.print("Nhập tên sách: ");
        return SCANNER.nextLine();
    }

    public String inputKind() {
        System.out.print("Nhập kiểu sách (1-Sách giáo khoa/ 2-Sách tham khảo/ 3-Truyện tranh/ 0-Khác): ");
        String kind = null;
        int choose = Integer.parseInt(SCANNER.nextLine());

        switch (choose) {
            case 1:
                kind = "Sách giáo khoa";
                break;
            case 2:
                kind = "Sách tham khảo";
                break;
            case 3:
                kind = "Truyện tranh";
                break;
            case 0:
                System.out.print("Mời nhập kiểu sách: ");
                kind = SCANNER.nextLine();
                break;
            default:
                System.out.println("Mời chọn chức năng trong menu!");
                break;
        }
        return kind;
    }

    public int inputPrice() {
        System.out.print("Nhập giá sách: ");
        while (true) {
            try {
                return Integer.parseInt(SCANNER.nextLine());
            } catch (Exception e) {
                System.out.println("Giá sách không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public int inputNumOfBook() {
        System.out.print("Nhập số lượng sách: ");
        while (true) {
            try {
                return Integer.parseInt(SCANNER.nextLine());
            } catch (Exception e) {
                System.out.println("Số lượng sách không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }

    public String inputStatus() {
        System.out.print("Nhập tình trạng sách (0-Cũ/ 1-Mới): ");
        String status = null;
        int choose = Integer.parseInt(SCANNER.nextLine());

        switch (choose) {
            case 0:
                status = "Cũ";
                break;
            case 1:
                status = "Mới";
                break;
            default:
                System.out.println("Mời chọn chức năng trong menu!");
                break;
        }
        return status;
    }

    public int inputDay() {
        System.out.print("Nhập số ngày muốn thuê sách: ");
        while (true) {
            try {
                return Integer.parseInt(SCANNER.nextLine());
            } catch (Exception e) {
                System.out.println("Số ngày không hợp lệ. Vui lòng nhập lại!");
            }
        }
    }
}
