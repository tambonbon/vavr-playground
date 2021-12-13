package Functions;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.*;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;

public class Currying {

    /**
     * Currying is a technique to partially apply a function ..
     * .. by FIXING a value for ONE of the params
     */
    public static void main(String[] args) {
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Function1<Integer, Integer> addNumber2 = sum.curried().apply(2);
        // the code is identical to the example in PartialApplication
        assertEquals(addNumber2.apply(4), 6);

        // The difference became clear when number of params increases
        Function3<Integer, Integer, Integer, Integer> sum3Num = (a, b, c) -> a + b + c;
        Function1<Integer, Function1<Integer, Integer>> addNumber2Curried  = sum3Num.curried().apply(2);
        assertEquals(addNumber2Curried.apply(2).apply(3), 7);
        Function1<Integer, Integer> addNumber2Partial = sum3Num.apply(2, 3);
        assertEquals(addNumber2Partial.apply(2), 7);
    }
}
