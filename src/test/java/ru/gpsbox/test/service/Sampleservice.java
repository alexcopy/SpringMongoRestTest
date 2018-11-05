package ru.gpsbox.test.service;

import java.util.List;
import java.util.function.Predicate;

public class Sampleservice {

    public static boolean isGreaterThen3(int number) {
        return number > 3;
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static int doubleIt(int number) {
        return number * 2;
    }

//TODO Imperative style of programming
    public int totalValues(List<Integer> numbers, Predicate<Integer> selector) {
        int result = 0;
        for (int e : numbers) {
            if (selector.test(e)) result += e;
        }
        return result;
    }

// ** Mixing object composition and function composition */
    public int totalValuesLambda(List<Integer> numbers, Predicate<Integer> selector) {
        return numbers.stream().filter(selector).reduce(0, Math::addExact);
    }


}
