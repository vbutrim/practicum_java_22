import java.util.Scanner;

/**
 * @author butrim
 */
public class CommandLineManager {
    Scanner scanner;
    Journal journal;

    public CommandLineManager(Scanner scanner, Journal journal) {
        this.scanner = scanner;
        this.journal = journal;
    }

    public void printMenuAndHandleCommandInfinitely() {
        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 0) {
                System.out.println("Пока!");
                return;
            }

            handleNotExitCommand(command);
        }
    }

    public static void printMenu() {
        System.out.println("Введите команду");
        System.out.println("1 -- ввод ученика");
        System.out.println("2 -- вывод статстики средних оценок");
        System.out.println("3 -- вывод кол-ва отличников");
        System.out.println("0 -- выход из программы");
    }

    public void handleNotExitCommand(int command) {
        if (command == 1) {
            readStudentAndAdd();
        } else if (command == 2) {
            printAverageMarks();
        } else if (command == 3) {
            printExcellentStudentsCount();
        } else {
            System.out.println("Неизвестная команда");
        }
    }

    public void readStudentAndAdd() {
        Student student = readStudent();
        journal.add(student);
    }

    public Student readStudent() {
        System.out.println("Введи имя: ");
        String studentName = scanner.next();
        System.out.println("Введи оценку за математику : ");
        int mathMark = scanner.nextInt();
        System.out.println("Введи оценку за русскимй язык: ");
        int russianLanguageMark = scanner.nextInt();
        System.out.println("Введи оценку за информатику: ");
        int informaticsMark = scanner.nextInt();

        return new Student(studentName, mathMark, russianLanguageMark, informaticsMark);
    }

    private void printAverageMarks() {
        for (int i = 0; i < journal.studentsCount; i++) {
            Student nextStudent = journal.students[i];
            System.out.println(nextStudent.name + ": " + nextStudent.getAverageMark());
        }
    }

    private void printExcellentStudentsCount() {
        System.out.println("Количество отличников " + journal.getExcellentStudentsCount());
    }
}
