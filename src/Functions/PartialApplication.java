package Functions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.vavr.Function1;
import io.vavr.Function2;

public class PartialApplication {
    
    /**
     * Partial application allows you to derive a new function ..
     * .. from an existing one by FIXING some values
     */

     public static void main(String[] args) {
         Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
         Function1<Integer, Integer> addTwo = sum.apply(2);

         assertEquals(addTwo.apply(4), 6);
     }
}
