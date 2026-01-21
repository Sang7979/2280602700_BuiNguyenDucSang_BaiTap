import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== CHƯƠNG TRÌNH QUẢN LÝ SÁCH =====");
            System.out.println("1. Thêm 1 cuốn sách");
            System.out.println("2. Xóa 1 cuốn sách");
            System.out.println("3. Thay đổi cuốn sách");
            System.out.println("4. Xuất thông tin tất cả sách");
            System.out.println("5. Tìm sách có tiêu đề chứa 'Lập trình'");
            System.out.println("6. Lấy tối đa K sách có giá <= P");
            System.out.println("7. Tìm sách theo danh sách tác giả");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    Book b = new Book();
                    b.input();
                    listBook.add(b);
                    System.out.println("✔ Thêm sách thành công");
                }

                case 2 -> {
                    System.out.print("Nhập mã sách cần xóa: ");
                    int id = Integer.parseInt(sc.nextLine());
                    listBook.removeIf(b -> b.getId() == id);
                }

                case 3 -> {
                    System.out.print("Nhập mã sách cần sửa: ");
                    int id = Integer.parseInt(sc.nextLine());
                    Book book = listBook.stream()
                            .filter(b -> b.getId() == id)
                            .findFirst()
                            .orElse(null);
                    if (book != null) {
                        book.input();
                    } else {
                        System.out.println("❌ Không tìm thấy sách");
                    }
                }

                case 4 -> listBook.forEach(Book::output);

                case 5 -> listBook.stream()
                        .filter(b -> b.getTitle().toLowerCase().contains("lập trình"))
                        .forEach(Book::output);

                case 6 -> {
                    System.out.print("Nhập K: ");
                    int k = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhập giá P: ");
                    double p = Double.parseDouble(sc.nextLine());
                    listBook.stream()
                            .filter(b -> b.getPrice() <= p)
                            .limit(k)
                            .forEach(Book::output);
                }

                case 7 -> {
                    System.out.print("Nhập số tác giả: ");
                    int n = Integer.parseInt(sc.nextLine());
                    Set<String> authors = new HashSet<>();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Tác giả " + (i + 1) + ": ");
                        authors.add(sc.nextLine().toLowerCase());
                    }
                    listBook.stream()
                            .filter(b -> authors.contains(b.getAuthor().toLowerCase()))
                            .forEach(Book::output);
                }

                case 0 -> System.out.println("👋 Thoát chương trình");

                default -> System.out.println("❌ Lựa chọn không hợp lệ");
            }
        } while (choice != 0);
    }
}
