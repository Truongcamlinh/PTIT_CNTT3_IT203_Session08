public class Student {
    private String id;
    private String name;
    private int age;
    private String gender;

    private double mathScore;
    private double physicsScore;
    private double chemistryScore;

    private double averageScore;
    private String rank;

    public Student(String id, String name, int age, String gender, double mathScore, double physicsScore, double chemistryScore) {
        this.id = id;
        setName(name);
        setAge(age);
        setGender(gender);

        setMath(mathScore);
        setPhysics(physicsScore);
        setChemistry(chemistryScore);

        calculateAvgAndRank();
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getGender(){
        return gender;
    }

    public double getMathScore(){
        return mathScore;
    }

    public double getPhysicsScore(){
        return physicsScore;
    }

    public double getChemistryScore(){
        return chemistryScore;
    }
    public double getAverageScore(){
        return averageScore;
    }

    public String getRank(){
        return rank;
    }

    public void setName(String name){
        if(name != null && !name.trim().isEmpty()){
            this.name = name;
        }
    }

    public void setAge(int age){
        if(age >= 18 && age <= 30)
            this.age = age;
    }

    public void setGender(String gender){
        if(gender != null && !gender.trim().isEmpty()){
            this.gender = gender;
        }
    }

    public void setMath(double math) {
        if (isValidScore(math)) {
            this.mathScore = math;
            calculateAvgAndRank();
        }
    }

    public void setPhysics(double physics) {
        if (isValidScore(physics)) {
            this.physicsScore = physics;
            calculateAvgAndRank();
        }
    }

    public void setChemistry(double chemistry) {
        if (isValidScore(chemistry)) {
            this.chemistryScore = chemistry;
            calculateAvgAndRank();
        }
    }

    private boolean isValidScore(double score) {
        return score >= 0 && score <= 10;
    }

    private void calculateAvgAndRank() {
        averageScore = (mathScore + physicsScore + chemistryScore) / 3;

        if (averageScore >= 8.0 && mathScore >= 6.5 && physicsScore >= 6.5 && chemistryScore >= 6.5) {
            rank = "Giỏi";
        } else if (averageScore >= 6.5 && mathScore >= 5.0 && physicsScore >= 5.0 && chemistryScore >= 5.0) {
            rank = "Khá";
        } else if (averageScore >= 5.0 && mathScore >= 3.5 && physicsScore >= 3.5 && chemistryScore >= 3.5) {
            rank = "Trung bình";
        } else {
            rank = "Yếu";
        }
    }

    public void display() {
        System.out.printf(
                "%-10s %-20s %-5d %-10s %-6.2f %-10s%n",
                id, name, age, gender, averageScore, rank
        );
    }
}