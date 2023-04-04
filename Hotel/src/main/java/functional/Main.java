package functional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> sorted = new ArrayList<>(
                List.of("b", "a", "c")
        );

        sorted.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(sorted);

        // 1: Function<Input, Output>
        // 2: BiFunction<Input1, Input2, Output>

        Function<String, String> UPPER_CASE = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };

        Function<String, String> LOWER_CASE = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toLowerCase();
            }
        };

        List<String> upperCaseSorted = applyFunctionOnEachObject(sorted, UPPER_CASE);
        List<String> lowerCaseSorted = applyFunctionOnEachObject(sorted, LOWER_CASE);

        System.out.println(
                upperCaseSorted
        );
        System.out.println(
                lowerCaseSorted
        );
    }

    private static List<String> applyFunctionOnEachObject(
            List<String> list,
            Function<String, String> function)
    {
        List<String> result = new ArrayList<>();

        for (String input : list) {
            String output = function.apply(input);
            result.add(output);
        }

        return result;
    }
}
