package CloneablePojoTest;



public class Student implements Cloneable {
    private Subject subject;
    private int age;

    public Student(Subject subject, int age) {
        this.subject = subject;
        this.age = age;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Object clone(){
        //浅拷贝
        try {
            Student student = (Student) super.clone();
            student.subject= (Subject) subject.clone();
            return student;
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "subject=" + subject +
                ", age=" + age +
                '}';
    }
}
