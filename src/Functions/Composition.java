package Functions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.function.BiFunction;
import java.util.function.Function;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function5;

public class Composition {
    
    static class FunctionsBasic {
        public static void main(String[] args) {

        //  In java..
        // .. if you'd like to take the sum of two integers with functional fashion..
        // .. you could do like this:
        BiFunction<Integer, Integer, Integer> normalBiFunction = (a, b) -> a + b;

        // However, what if I'd like to take the sum of 5 integers? That's hard!

        // With Vavr, you can declare a function taking at most 22 parameters
        Function2<Integer, Integer, Integer> vavFunction2 = (a, b) -> a + b;
        Function5<Integer, Integer, Integer, Integer, Integer, Integer> vavrFunction5 = (a, b, c, d, e) -> a + b + c + d + e;
        








        
        Function2<Integer, Integer, Integer> sumLambda = (a, b) -> a + b;

        // the following is a long-hand anonymous class def of the above:
        Function2<Integer, Integer, Integer> sumAnony = new Function2<Integer,Integer,Integer>(){
            @Override
            public Integer apply(Integer arg0, Integer arg1) {
                return arg0 + arg1;
            }
        };

        assertEquals(sumLambda.apply(1, 2), 3);
        assertEquals(sumAnony.apply(1, 2), 3);
        assertNotEquals(sumAnony.apply(1, 2), 4);
       }
    }
    
    public static void main(String[] args) {
        
        // f: X -> Y, g: Y -> Z ===> composed: h: g(f(x)): X -> Z
        Function1<Integer, Integer> plusOne = (a) -> a + 1;
        Function1<Integer, Integer> timesFour = a -> a*4;

        // we can use `andThen` or `compose`:
        Function1<Integer, Integer> addOneThenTimeFour = plusOne.andThen(timesFour);
        Function1<Integer, Integer> addOneComposedTimeFour = plusOne.compose(timesFour);

        assertEquals(addOneThenTimeFour.apply(2), 12);

    }
}
