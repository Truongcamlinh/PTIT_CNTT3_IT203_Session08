void main(){
    Scanner sc = new Scanner(System.in);
    StudentManager sm = new StudentManager();

    while(true){
        System.out.println("\n===== STUDENT MANAGEMENT =====");
        System.out.println("1. Add student");
        System.out.println("2. Display all students");
        System.out.println("3. Find student by ID");
        System.out.println("4. Sort by average score");
        System.out.println("0. Exit");
        System.out.print("Choose: ");

        int choice = Integer.parseInt(sc.nextLine());

        switch(choice){
            case 1:
                System.out.print("Student ID: ");
                String id = sc.nextLine();
        }
    }
}