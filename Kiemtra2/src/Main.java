import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student[] students = null;
        int choice;

        do {
            System.out.println("===== QUAN LY DIEM SINH VIEN =====");
            System.out.println("1. Nhap danh sach sinh vien");
            System.out.println("2. Hien thi danh sach sinh vien");
            System.out.println("3. Tim kiem sinh vien theo Hoc luc");
            System.out.println("4. Sap xep theo hoc luc giam dan");
            System.out.println("5. Thoat");
            System.out.println("==================================");
            System.out.print("Chon chuc nang: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    students = nhapDanhSach(scanner);
                    break;
                case 2:
                    hienThiDanhSach(students);
                    break;
                case 3:
                    timKiemTheoHocLuc(scanner, students);
                    break;
                case 4:
                    sapXepTheoHocLuc(students);
                    break;
                case 5:
                    System.out.println("Thoat chuong trinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
            System.out.println();
        } while (choice != 5);

        scanner.close();
    }

    public static Student[] nhapDanhSach(Scanner scanner) {
        System.out.print("Nhap so luong sinh vien: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        Student[] students = new Student[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap thong tin sinh vien thu " + (i + 1) + ":");

            String id;
            while (true) {
                System.out.print("Nhap ma sinh vien (VD: SV001): ");
                id = scanner.nextLine();
                if (id.startsWith("SV") && id.length() == 5) {
                    try {
                        Integer.parseInt(id.substring(2));
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Ma sinh vien khong hop le! Phai co dang SV + 3 chu so.");
                    }
                } else {
                    System.out.println("Ma sinh vien khong hop le! Phai co dang SV + 3 chu so.");
                }
            }
            System.out.print("Nhap ten sinh vien: ");
            String name = scanner.nextLine();
            System.out.print("Nhap diem trung binh: ");
            double score = scanner.nextDouble();
            scanner.nextLine();
            students[i] = new Student(id, name, score);
        }
        System.out.println("Nhap danh sach thanh cong!");
        return students;
    }
    public static void hienThiDanhSach(Student[] students) {
        if (students == null || students.length == 0) {
            System.out.println("Danh sach rong!");
            return;
        }
        System.out.println("Danh sach sinh vien:");
        for (int i = 0; i < students.length; i++) {
            System.out.println((i + 1) + ". " + students[i].toString());
        }
    }

    public static void timKiemTheoHocLuc(Scanner scanner, Student[] students) {
        if (students == null || students.length == 0) {
            System.out.println("Danh sach rong!");
            return;
        }
        System.out.print("Nhap hoc luc can tim (Gioi/Kha/Trung Binh): ");
        String rank = scanner.nextLine();
        System.out.println("Sinh vien co hoc luc " + rank + ":");
        boolean found = false;
        for (int i = 0; i < students.length; i++) {
            if (students[i].getRank().equals(rank)) {
                System.out.println(students[i].toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay sinh vien nao!");
        }
    }

    public static void sapXepTheoHocLuc(Student[] students) {
        if (students == null || students.length == 0) {
            System.out.println("Danh sach rong!");
            return;
        }
        for (int i = 0; i < students.length - 1; i++) {
            for (int j = i + 1; j < students.length; j++) {
                if (students[i].getScore() < students[j].getScore()) {
                    Student temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;
                }
            }
        }

        System.out.println("Sap xep thanh cong!");
        hienThiDanhSach(students);
    }
}
