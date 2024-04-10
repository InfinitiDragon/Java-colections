import java.util.*;







public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        University university = new University();

        while (true) {
            System.out.println("1. Додати студента");
            System.out.println("2. Видалити студента");
            System.out.println("3. Додати групу");
            System.out.println("4. Видалити групу");
            System.out.println("5. Кількість студентів в групі");
            System.out.println("6. Загальна кількість студентів");
            System.out.println("7. Переглянути всі групи");
            System.out.println("8. Переглянути всіх студентів");
            System.out.println("9. Переглянути кількість студентів у групі та їх імена");
            System.out.println("10. Вихід");
            System.out.print("Виберіть опцію: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addStudent(scanner, university);
                    break;
                case 2:
                    removeStudent(scanner, university);
                    break;
                case 3:
                    addGroup(scanner, university);
                    break;
                case 4:
                    removeGroup(scanner, university);
                    break;
                case 5:
                    System.out.print("Введіть назву групи: ");
                    String groupToCheck = scanner.nextLine();
                    Group groupToInspect = findGroupByName(university, groupToCheck);
                    if (groupToInspect != null) {
                        System.out.println("Кількість студентів в групі " + groupToInspect.getName() + ": " + groupToInspect.getStudentCount());
                    } else {
                        System.out.println("Групу не знайдено.");
                    }
                    break;
                case 6:
                    System.out.println("Загальна кількість студентів в університеті: " + university.getTotalStudentCount());
                    break;
                case 7:
                    System.out.println("Список усіх груп:");
                    for (Group grp : university.getGroups()) {
                        System.out.println(grp.getName());
                    }
                    break;
                case 8:
                    System.out.println("Список усіх студентів:");
                    for (Student std : university.getAllStudents()) {
                        System.out.println("ID: " + std.getId() + ", Ім'я: " + std.getName());
                    }
                    break;
                case 9:
                    System.out.print("Введіть назву групи: ");
                    String groupToCount = scanner.nextLine();
                    university.printStudentsInGroup(groupToCount);
                    break;
                case 10:
                    System.out.println("До побачення!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
            }
        }
    }

    private static void removeGroup(Scanner scanner, University university) {
        System.out.print("Введіть назву групи для видалення: ");
        String groupToDelete = scanner.nextLine();
        Group groupToRemove = findGroupByName(university, groupToDelete);
        if (groupToRemove != null) {
            university.removeGroup(groupToRemove);
            System.out.println("Групу " + groupToRemove.getName() + " видалено.");
        } else {
            System.out.println("Групу не знайдено.");
        }
    }

    private static void addGroup(Scanner scanner, University university) {
        System.out.print("Введіть назву групи: ");
        String newGroupName = scanner.nextLine();
        Group newGroup = new Group(newGroupName);
        university.addGroup(newGroup);
        System.out.println("Група " + newGroup.getName() + " створена.");
    }

    private static void removeStudent(Scanner scanner, University university) {
        System.out.print("Введіть ім'я або ID студента для видалення: ");
        String studentIdentifier = scanner.nextLine();
        removeStudent(university, studentIdentifier);
        return;
    }

    private static void addStudent(Scanner scanner, University university) {
        System.out.print("Введіть ім'я студента: ");
        String studentName = scanner.nextLine();
        Student student = new Student(studentName);
        System.out.print("У яку групу додати (введіть назву): ");
        String groupName = scanner.nextLine();
        Group group = findGroupByName(university, groupName);
        if (group != null) {
            group.addStudent(student);
            System.out.println("Студент доданий до групи " + group.getName() + " з ID " + student.getId());
        } else {
            System.out.println("Групу не знайдено.");
        }
    }

    private static Group findGroupByName(University university, String name) {
        for (Group group : university.getGroups()) {
            if (group.getName().equalsIgnoreCase(name)) {
                return group;
            }
        }
        return null;
    }

    private static void removeStudent(University university, String identifier) {
        boolean removed = false;
        for (Group group : university.getGroups()) {
            for (Student student : group.getStudents()) {
                if (student.getName().equalsIgnoreCase(identifier) || String.valueOf(student.getId()).equals(identifier)) {
                    group.removeStudent(student);
                    removed = true;
                    break;
                }
            }
            if (removed) {
                break;
            }
        }
        if (removed) {
            System.out.println("Студент видалений.");
        } else {
            System.out.println("Студент не знайдений.");
        }
    }
}
