package Functions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import io.vavr.Function1;
import io.vavr.Function2;

public class Composition {
    
    static class FunctionsBasic {
        public static void main(String[] args) {

        // the following lambda creates a funciton to sum 2 integers:
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
