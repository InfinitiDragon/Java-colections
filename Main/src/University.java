import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class University {
    private List<Group> groups;

    public University() {
        this.groups = new ArrayList<>();
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void removeGroup(Group group) {
        groups.remove(group);
    }

    public int getTotalStudentCount() {
        int total = 0;
        for (Group group : groups) {
            total += group.getStudentCount();
        }
        return total;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Student> getAllStudents() {
        List<Student> allStudents = new LinkedList<>();
        for (Group group : groups) {
            allStudents.addAll(group.getStudents());
        }
        return allStudents;
    }

    public int getStudentCountInGroup(String groupName) {
        Group group = findGroupByName(groupName);
        if (group != null) {
            return group.getStudentCount();
        }
        return 0;
    }

    public void printStudentsInGroup(String groupName) {
        Group group = findGroupByName(groupName);
        if (group != null) {
            System.out.println("Студенти в групі " + group.getName() + ":");
            for (Student student : group.getStudents()) {
                System.out.println("ID: " + student.getId() + ", Ім'я: " + student.getName());
            }
        } else {
            System.out.println("Групу не знайдено.");
        }
    }

    private Group findGroupByName(String name) {
        for (Group group : groups) {
            if (group.getName().equalsIgnoreCase(name)) {
                return group;
            }
        }
        return null;
    }
}