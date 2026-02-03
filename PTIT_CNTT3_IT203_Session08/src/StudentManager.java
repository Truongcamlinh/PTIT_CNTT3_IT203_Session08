import java.text.ParsePosition;
import java.util.Scanner;

public class StudentManager {
    private static final int MAX = 100;
    private static Student[] students = new Student[MAX];
    private static int size = 0;

    private Scanner sc = new Scanner(System.in);

    public void menu() {
        int choice;
        do {
            System.out.println("===== QUẢN LÝ SINH VIÊN =====");
            System.out.println("1. Thêm sinh viên mới");
            System.out.println("2. Hiển thị tất cả sinh viên");
            System.out.println("3. Tìm kiếm sinh viên");
            System.out.println("4. Cập nhật thông tin sinh viên");
            System.out.println("5. Xóa sinh viên");
            System.out.println("6. Tính điểm trung bình và xếp loại");
            System.out.println("7. Sắp xếp sinh viên");
            System.out.println("8. Thống kê");
            System.out.println("9. Thoát");
            System.out.print("Chọn chức năng (1-9): ");


            choice = readInt();


            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayAll();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    recalculateAll();
                    break;
                case 7:
                    sortMenu();
                    break;
                case 8:
                    statistics();
                    break;
                case 9:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 9);
    }

    private void addStudent() {
        if (size >= MAX) {
            System.out.println("Danh sách sinh viên đã đầy!");
            return;
        }

        System.out.print("Mã SV: ");
        String id = sc.nextLine();
        if (findById(id) != -1) {
            System.out.println("Mã sinh viên đã tồn tại!");
            return;
        }

        System.out.print("Họ tên: ");
        String name = sc.nextLine();


        System.out.print("Tuổi: ");
        int age = readInt();


        System.out.print("Giới tính: ");
        String gender = sc.nextLine();


        System.out.print("Điểm Toán: ");
        double math = readDouble();
        System.out.print("Điểm Lý: ");
        double physics = readDouble();
        System.out.print("Điểm Hóa: ");
        double chemistry = readDouble();

        students[size++] = new Student(id, name, age, gender, math, physics, chemistry);
        System.out.println("Thêm sinh viên thành công.");
    }

    private void displayAll() {
        if (size == 0) {
            System.out.println("Danh sách trống");
            return;
        }

        System.out.printf("%-10s %-20s %-5s %-10s %-6s %-10s%n",
                "Mã SV", "Họ tên", "Tuổi", "Giới tính", "ĐTB", "Xếp loại");


        for (int i = 0; i < size; i++) {
            students[i].display();
        }
    }

    private void searchStudent() {
        if (size == 0) {
            System.out.println("Danh sách tổng trống");
            return;
        }
        String key = sc.nextLine().toLowerCase();
        boolean found = false;
        for (int i = 0; i < size; ++i) {
            if (students[i].getId().equalsIgnoreCase(key)
                    || students[i].getName().toLowerCase().contains(key)) {
                students[i].display();
                found = true;
            }
        }
        if (!found) System.out.println("Không tìm thấy sinh viên!");
    }

    private void updateStudent() {
        System.out.print("Nhập mã SV cần cập nhập: ");
        String id = sc.nextLine();
        int index = findById(id);

        if (index == -1) {
            System.out.println("Không tìm thấy sinh viên");
            return;
        }

        Student s = students[index];


        System.out.print("Tên mới: ");
        s.setName(sc.nextLine());


        System.out.print("Tuổi mới: ");
        s.setAge(readInt());
        System.out.print("Điểm Toán mới: ");
        s.setMath(readDouble());
        System.out.print("Điểm Lý mới: ");
        s.setPhysics(readDouble());
        System.out.print("Điểm Hóa mới: ");
        s.setChemistry(readDouble());

        System.out.println("Cập nhật thành công!");
    }

    private void deleteStudent() {
        System.out.print("Nhập mã SV cần xoá: ");
        String id = sc.nextLine();
        int index = findById(id);

        if (index == -1) {
            System.out.println("Không tìm thấy sinh viên");
            return;
        }
        System.out.print("Bạn có chắc muốn xoá? (y/n): ");
        if (!sc.nextLine().equalsIgnoreCase("y")) {
            return;
        }

        for (int i = index; i < size - 1; i++) {
            students[i] = students[i + 1];
        }
        size--;
        System.out.println("Đã xoá sinh viên!");
    }

    private void recalculateAll() {
        for (int i = 0; i < size; i++) {

        }
        System.out.println("Điểm đã được cập nhập tự động.");
    }

    private void sortMenu() {
        System.out.println("1. Theo DTB giảm dần (Bubble sort)");
        System.out.println("2. Theo tên A-Z (Selection sort");
        int c = readInt();
        if (c == 1) sortByAvgDesc();
        else if (c == 2) sortByName();
    }

    private void sortByAvgDesc() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (students[i].getAverageScore() < students[j].getAverageScore()) {
                    Student temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;
                }
            }
        }
        System.out.println("Đã sắp xếp theo điểm Tb giảm dần.");
    }

    private void sortByName() {
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (students[j].getName().compareToIgnoreCase(students[min].getName()) < 0) {
                    min = j;
                }
            }
            Student tmp = students[i];
            students[i] = students[min];
            students[min] = tmp;
        }
        System.out.println("Đã sắp xếp theo tên A-Z.");
    }

    private void statistics() {
        int gioi = 0, kha = 0, tb = 0, yeu = 0;
        double sum = 0;
        Student max = students[0];
        Student min = students[0];
        for (int i = 0; i < size; i++) {
            sum += students[i].getAverageScore();
            if (students[i].getAverageScore() > max.getAverageScore()) {
                max = students[i];
            }
            if (students[i].getAverageScore() < min.getAverageScore()) {
                min = students[i];
            }
            switch (students[i].getRank()) {
                case "Giỏi": gioi++; break;
                case "Khá": kha++; break;
                case "Trung bình": tb++; break;
                default: yeu++;
            }
        }
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Nhập số nguyên hợp lệ: ");
            }
        }
    }

    private double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Nhập số hợp lệ: ");
            }
        }
    }

    private int findById(String id) {
        for (int i = 0; i < size; ++i) {
            if (students[i].getId().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }
}
