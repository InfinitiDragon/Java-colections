import java.util.ArrayList;
import java.util.List;

class Group {
    private static int groupCounter = 1;
    private int id;
    private String name;
    private List<Student> students;

    public Group(String name) {
        this.id = groupCounter++;
        this.name = name;
        this.students = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public int getStudentCount() {
        return students.size();
    }

    public List<Student> getStudents() {
        return students;
    }
}