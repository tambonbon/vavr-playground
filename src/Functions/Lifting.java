package Functions;

import io.vavr.Function2;
import io.vavr.control.Option;

public class Lifting {
    
    // WE can lift a partial function into a total function returning an Option
    // Partial function from X to Y is a function f: X' -> Y for some subset X' of X
    // It generalizes the concept of f: X->Y ..
    // .. by NOT mapping f to every element of X to an element of Y
    // ---> Partial function works properly only for some input value ..
    // .. like, 1 success, 1 fail

    public static void main(String[] args) {
        // Define a `divide` method which is a partial function
        Function2<Integer, Integer, Integer> divide = (a, b) -> a/b;

        // We use `lift` to turn `divide` into a total function for all inputs
        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);

        Option<Integer> i1 = safeDivide.apply(1, 0);
        System.out.println(i1); // None

        Option<Integer> i2 = safeDivide.apply(4, 2);
        System.out.println(i2); // Some(2)

        // We can lift an explicitly defined partial function like this ..
        // .. which explicitly throws exceptions
        class LiftPFThrowingExceptions {

            int sum(int first, int second) { 
                if (first < 0 || second < 0) {
                    throw new IllegalArgumentException("Only positive integers are allowed");
                }
            return first + second;
            }
                    
            // Now we lift `sum` by using method references
            Function2<Integer, Integer, Option<Integer>> sum = Function2.lift(this::sum);

            Option<Integer> i3 = sum.apply(-1, 2);
            }
            
        System.out.println(new LiftPFThrowingExceptions().i3); // None
    }
   
}
