package Values;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import io.vavr.control.Option;

public class VavrOption {
    
    /**
     * `Option` is a monadic, represents an optional value.
     * either Some or None
     * 
     * DIFFERENCES BETWEEN JAVA's OPTIONAL & vavr's OPTION:
     * - `Optional`: a call to `.map` that results in a null ---> result in empty `Optional`
     * - `Option`: ---> Some(null) ---> NPE
     */

    public static void main(String[] args) {
        
        // an Optional that can result in an empty Optional w/o any exceptions
        Optional<String> maybeFoo = Optional.of("foo");
        assertEquals(maybeFoo.get(), "foo");

        Optional<String> maybeFooBar = maybeFoo.map(s -> (String)null) // this maps foo to null but results in empty, or None
                                                .map(s -> s.toUpperCase() + "bar");
        assertEquals(maybeFooBar.isEmpty(), true); // no value present

        // an Option
        Option<String> optionFoo = Option.of("foo");
        assertEquals(optionFoo.get(), "foo");

        Option<String> optionNone = Option.none();
        //   System.out.println(optionNone.get()); // NoSuchElement (No value present)
        Optional<String> optionalNone = Optional.empty();
        // System.out.println(optionalNone.get()); // No value present

        // If an option is None (empty), then mapping it to null still results in None
        assertTrue(optionNone.map(s -> (String) null).isEmpty()); // None
        // If an option is Some(something), then mapping it to null results in Some(null)
        assertFalse(optionFoo.map(s -> (String) null).isEmpty());

        // Is it the case in Optional? If an optional is Some(something), then mapping it to null results in None
        assertTrue(maybeFoo.map(s -> (String) null).isEmpty());

        // Option<String> optionFooBar = optionFoo.map(s -> (String) null ) // this results in a null, or Some(Null)
                                                // .map(s -> s.toUpperCase() + "bar"); // .. NPE here
        // assertEquals(optionFooBar.isDefined(), new NullPointerException());

        /**
         * Seems counter-intuitive, but it adheres to the requirements of a monad ..
         * .. to maintain computational context when calling `.map`
         * 
         * - In `Option`, mapping on a Some -> Some, None -> None
         * - In `Optional`, mapping on a Some (may) -> None
         * 
         * Correct way: use `flatMap`
         */

        Option<String> optionFooBarFlatMap = optionFoo.map(s -> (String) null) // results in Some(Null)
                                                        .flatMap(s -> Option.of(s) // s, which is null, becomes None
                                                                            .map(t -> t.toUpperCase() + "bar"));
        assertEquals(optionFooBarFlatMap.isEmpty(), true); // No value present        

        // Alternatively, move `flatMap` to be co-located with possibly `null` value
        Option<String> optionFooBarFlatMap2 = optionFoo.flatMap(s -> Option.of((String) null)) // results in None
                                                        .map(s -> s.toUpperCase() + "bar");



     }
}
