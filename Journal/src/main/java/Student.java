/**
 * @author butrim
 */
public class Student {
    String name;
    // option 1: int[] ->
    // option 2: mathMark, russianLanguageMark, ...mark
    // option 3: class MathMark{ int mark }
    // option 4: HashMap<String, Integer>

    int mathMark;
    int russianLanguageMark;
    int informaticsMark;

    public Student(String name, int mathMark, int russianLanguageMark, int informaticsMark) {
        this.name = name;
        this.mathMark = mathMark;
        this.russianLanguageMark = russianLanguageMark;
        this.informaticsMark = informaticsMark;
    }

    public double getAverageMark() {
        return (this.mathMark + this.russianLanguageMark + this.informaticsMark) / 3.0;
    }

    public boolean hasExcellentMarksOnly() {
        return mathMark == 5 && russianLanguageMark == 5 && informaticsMark == 5;
    }
}
