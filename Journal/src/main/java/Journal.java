/**
 * @author butrim
 */
public class Journal {
    // option 1: Student[]
    // option 2: Student student1, Student student2, ...,
    // option 3?: HashMap<String, Student>

    // shit:
    // String[] names
    // int[] mathMarks
    // int[] russianLanguageMarks
    // int[] informaticsMarks
    // int[] englishMarks

    Student[] students;
    int studentsCount;

    // option 1: #students
    Journal() {
        this.students = new Student[1_000];
        this.studentsCount = 0;
    }

/*    // option 2
    Journal(Student[] students) {
        this.students = students;
    }*/

    public void add(Student student) {
        this.students[studentsCount++] = student;
    }

    public int getExcellentStudentsCount() {
        int excellentStudentsCount = 0;

        for (int i = 0; i < studentsCount; i++) {
            Student student = students[i];
            if (student.hasExcellentMarksOnly()) {
                excellentStudentsCount++;
            }
        }

        return excellentStudentsCount;
    }

}
