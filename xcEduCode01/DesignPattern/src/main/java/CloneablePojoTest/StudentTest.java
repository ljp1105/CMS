package CloneablePojoTest;

public class StudentTest {
    public static void main(String[] args) {
        Subject subject = new Subject("ssss");
        Student studentA = new Student(subject, 2);
        Student studentB = (Student) studentA.clone();
        Subject subject1 = studentB.getSubject();
        subject1.setName("aaaaa");
        studentB.setAge(4);
        System.out.println(studentA);
        System.out.println(studentB);
    }
}
